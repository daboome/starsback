package org.daboo.stars;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.daboo.stars.bootstrap.DataBootstrap;
import org.daboo.stars.domain.dto.StarsCollection;
import org.daboo.stars.domain.entity.Star;
import org.daboo.stars.domain.enums.StarType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class DemoApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	DataBootstrap bootstrap;

	private ObjectMapper mapper;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() {
		mapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	public void testApiUnavailableToUnauthorized() throws Exception {
		mockMvc.perform(get("/stars/all"))
				.andExpect(status().is4xxClientError()); // Expect that Unauthorized user is forbidden
	}

	@Test
	@WithMockUser
	public void testApiAvailableToAuthorized() throws Exception {
		mockMvc.perform(get("/stars/all"))
				.andExpect(status().isOk()); // Expect that User with default USER_ROLE accepted
	}

	@Test
	@WithMockUser
	public void testApiGetStarsCollection() throws Exception {
		RequestBuilder request = get("/stars/all");
		MvcResult mvcResult = mockMvc.perform(request).andReturn();
		String contentAsString = mvcResult.getResponse().getContentAsString();
		int status = mvcResult.getResponse().getStatus();

		StarsCollection starsCollection = mapper.readValue(contentAsString, StarsCollection.class);

		assertThat(status, is(HttpStatus.OK.value()));
		assertThat(starsCollection.getAllStars().size(), is(bootstrap.getStarsCount())); // Expect that star count is valid
	}

	@Test
	@WithMockUser
	public void testApiPostNewStar() throws Exception{
		Star star = new Star("testStarName", "testStarXCoord", "testStarYCoord", StarType.BLUE, "testDiscoveredPerson");
		String postStarString = mapper.writeValueAsString(star);

		RequestBuilder postRequest = post("/stars")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(postStarString);

		MvcResult postResult = mockMvc.perform(postRequest).andReturn();
		String postResultString = postResult.getResponse().getContentAsString();

		int postStatus = postResult.getResponse().getStatus();
		StarsCollection starsCollection = mapper.readValue(postResultString, StarsCollection.class);

		assertThat(postStatus, is(HttpStatus.OK.value()));
		assertThat(starsCollection.getAllStars().size(), is(bootstrap.getStarsCount() + 1)); // Expect that star count increased by 1
	}

	@Test
	@WithMockUser
	public void testApiPostEditExistingStar() throws Exception{
		String newStarName = "newStarName";
		String newStarXCoord = "newStarXCoord";
		String newStarYCoord = "newStarYCoord";
		String newStarDiscoveredPersonName = "newStarDiscoveredPersonName";
		long starId = 1L;
		int starVersion = 0;
		Star existingStar = new Star(newStarName, newStarXCoord, newStarYCoord, StarType.values()[0], newStarDiscoveredPersonName);
		existingStar.setId(starId);
		existingStar.setVersion(starVersion);

		String postStarString = mapper.writeValueAsString(existingStar);

		RequestBuilder postRequest = post("/stars")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(postStarString);

		MvcResult postResult = mockMvc.perform(postRequest).andReturn();
		String postResultString = postResult.getResponse().getContentAsString();

		int postStatus = postResult.getResponse().getStatus();
		StarsCollection starsCollection = mapper.readValue(postResultString, StarsCollection.class);

		assertThat(postStatus, is(HttpStatus.OK.value()));
		assertThat(starsCollection.getAllStars().size(), is(bootstrap.getStarsCount())); // Expect that star total amount is the same
		assertThat(starsCollection.getAllStars().get(0).getId(), is(starId));
		assertThat(starsCollection.getAllStars().get(0).getVersion(), is(starVersion + 1)); // Expect that existing star version number increased
		assertThat(starsCollection.getAllStars().get(0).getStarName(), is(newStarName));
		assertThat(starsCollection.getAllStars().get(0).getXcoord(), is(newStarXCoord));
		assertThat(starsCollection.getAllStars().get(0).getYcoord(), is(newStarYCoord));
		assertThat(starsCollection.getAllStars().get(0).getDiscoveredPerson(), is(newStarDiscoveredPersonName));
	}

	@Test
	@WithMockUser
	public void testApiDeleteStar() throws Exception {
		Long id = 1L;
		RequestBuilder request = get("/stars/delete/{id}", id);
		MvcResult mvcResult = mockMvc.perform(request).andReturn();
		String contentAsString = mvcResult.getResponse().getContentAsString();
		int status = mvcResult.getResponse().getStatus();

		StarsCollection starsCollection = mapper.readValue(contentAsString, StarsCollection.class);

		assertThat(status, is(HttpStatus.OK.value()));
		assertThat(starsCollection.getAllStars().size(), is(bootstrap.getStarsCount() - 1)); // Expect that star count decreased
	}
}
