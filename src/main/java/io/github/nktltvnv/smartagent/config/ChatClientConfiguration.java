package io.github.nktltvnv.smartagent.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfiguration {

    @Bean
    public ChatClient toolsChatClient(final ChatClient.Builder chatClientBuilder, final ToolCallbackProvider tools) {
        return chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultToolCallbacks(tools)
                .build();
    }
}
