package com.webapk.webchat.model;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {
private String content;
private String sender;

private Message type;
}
