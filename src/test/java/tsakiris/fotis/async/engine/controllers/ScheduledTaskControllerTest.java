package tsakiris.fotis.async.engine.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tsakiris.fotis.async.engine.Application;
import tsakiris.fotis.async.engine.common.Consts;
import tsakiris.fotis.async.engine.domain.ScheduledTask;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class ScheduledTaskControllerTest extends AbstractControllerTest {

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        if (Application.configurableApplicationContext == null) {
            Application.configurableApplicationContext = SpringApplication.run(Application.class, "");
        }
    }

    @Test
    public void create() throws Exception {
        this.mockMvc.perform(post(ScheduledTaskController.SVC_PATH)
                .header(Consts.APPLICATION_HEADER, Consts.APP_NAME)
                .contentType(contentTypeJson)
                .content(json(new ScheduledTask(null,null, "* * * * *"
                ))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(contentTypeJson))
        ;
    }

}