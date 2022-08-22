package com.demo.loaddev.Message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDoc{
    private String deviceName;
    private MessageEnum handle;
}
