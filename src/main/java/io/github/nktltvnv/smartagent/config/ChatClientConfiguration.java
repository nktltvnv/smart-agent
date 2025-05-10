package io.github.nktltvnv.smartagent.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "spring.ai.mcp.client.toolcallback", name = "enabled", havingValue = "true")
    public ChatClient toolsChatClient(final ChatClient.Builder chatClientBuilder, final ToolCallbackProvider tools) {
        return defaultChatClientBuilder(chatClientBuilder)
                .defaultToolCallbacks(tools)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatClient chatClient(final ChatClient.Builder chatClientBuilder) {
        return defaultChatClientBuilder(chatClientBuilder).build();
    }

    protected ChatClient.Builder defaultChatClientBuilder(final ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder.defaultAdvisors(new SimpleLoggerAdvisor());
    }
}
