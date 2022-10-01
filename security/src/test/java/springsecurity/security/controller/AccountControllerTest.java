package springsecurity.security.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import springsecurity.security.domain.Account;
import springsecurity.security.service.AccountService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    @Test
    public void requestAdminPageByUserWithForbidden() throws Exception {
        String username = "test";

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/admin")
                .with(SecurityMockMvcRequestPostProcessors.user(username).roles("USER")));

        actions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin" , roles = "ADMIN")
    public void requestAdminPageByAdmin() throws Exception {

        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/admin"));

        actions.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    @Transactional
    @DisplayName("로그인 성공")
    public void test1() throws Exception {
        String username = "test";
        String password = "123";

        Account account = createUser(username, password);

        ResultActions actions = mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                .user(username)
                .password(password));

        actions.andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @Transactional
    @DisplayName("로그인 실패")
    public void test2() throws Exception {
        String username = "test";
        String password = "123";

        Account account = createUser(username, password);

        ResultActions actions = mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin()
                .user(username)
                .password("1234qwer"));

        actions.andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }

    private Account createUser(String username, String password) {
        Account account = Account.builder()
                .username(username)
                .password(password)
                .role("USER")
                .build();

        return accountService.createUser(account);

    }

}