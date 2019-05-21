/**
 * 
 */
package bean;

/**
 * @author Somil Khandelwal
 *
 */
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"mobileNumber",
"otp",
"timeStamp"
})
public class SentMessageBean {

@JsonProperty("id")
private String id;
@JsonProperty("name")
private String name;
@JsonProperty("mobileNumber")
private String mobileNumber;
@JsonProperty("otp")
private String otp;
@JsonProperty("timeStamp")
private String timeStamp;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public String getId() {
return id;
}

@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("mobileNumber")
public String getMobileNumber() {
return mobileNumber;
}

@JsonProperty("mobileNumber")
public void setMobileNumber(String mobileNumber) {
this.mobileNumber = mobileNumber;
}
@JsonProperty("timeStamp")
public String getTimeStamp() {
return timeStamp;
}

@JsonProperty("timeStamp")
public void setTimeStamp(String timeStamp) {
this.timeStamp = timeStamp;
}

@JsonProperty("otp")
public String getOtp() {
return otp;
}

@JsonProperty("otp")
public void setOtp(String otp) {
this.otp = otp;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
