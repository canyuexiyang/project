package com.guhui.es.bboss.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: guhui
 * @CreateDate: 2019/4/8$ 16:37$
 * @Version: 1.0
 */
@Data
@ToString
public class Demo implements Serializable {

	private Integer demoId;

	private String agentStarttime;

	private String applicationName;

	private String Contentbody;



}
