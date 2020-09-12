/*
 * Copyright (c) 2019 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */
/**
 * 
 */
package com.automationanywhere.botcommand.date;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.AttributeType.NUMBER;

import static com.automationanywhere.commandsdk.model.DataType.STRING;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;

/**
 * @author Raj Singh Sisodia
 *
 */
@BotCommand              
@CommandPkg(label="DayofWeeks", name="DayofWeeks", description="DayofWeeks", icon="pkg.svg",
//           
node_label="DayofWeeks {{sourceString}}",
//       반환 타입                                       반환 값 내용 설명
return_type=STRING, return_label="입력 요일 출력", return_required=true)
public class DayofWeeks {
	
	private static final Messages MESSAGES = MessagesFactory.getMessages("com.automationanywhere.botcommand.demo.messages");
	
	@Execute
	public Value<String> action(@Idx(index = "1", type = TEXT) @Pkg(label = "Source string", default_value_type = STRING) @NotEmpty String sourceString) {
		
		if("".equals(sourceString.trim()))
			throw new BotCommandException(MESSAGES.getString("emptyInputString", "sourceString"));


		String result = sourceString.toUpperCase();
		
		return new StringValue(result);
		
	}
	

}
