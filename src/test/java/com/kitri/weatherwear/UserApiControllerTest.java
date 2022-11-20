package com.kitri.weatherwear;

import com.kitri.weatherwear.service.UserDaoService;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserDaoService userDaoService;

    @After
    public void tearDown() throws Exception {
        userDaoService.deleteById("test");
    }

 /*   @Test
    public void saveUser() throws Exception { //fail
        String id = "test";
        String pwd = "1234";
        String name = "test";
        String loc_latitude = "12345.2342";
        String loc_longitude = "3455.99997";
        String role = "USER";
        //작성중
        User user = new User(id, pwd, name, loc_latitude,loc_longitude,role);
        String url = "http://localhost:" + port + "/api/v1/users";

        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url, user, User.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(User.class);

        List<User> all = userDaoService.findAll();
        assertThat(all.lastIndexOf(user)).isGreaterThan(-1);
        assertThat(all.get(0).getId()).isEqualTo(id);
        assertThat(all.get(0).getPwd()).isEqualTo(pwd);
    }*/
}
