package io.github.deepseek4j_chat.client;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ChatResponse {
    private String model;
    private Date created_at;
    private String response;
    private boolean done;
    private String done_reason;
    private ArrayList<Integer> context;
    private long total_duration;
    private long load_duration;
    private long prompt_eval_count;
    private long prompt_eval_duration;
    private long eval_count;
    private long eval_duration;
}
