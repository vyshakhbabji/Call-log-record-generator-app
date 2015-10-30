package CallLogGenerator;

import http.APIResponse;

import org.json.JSONObject;

import platform.Platform;

import com.squareup.okhttp.RequestBody;

public class RingOut {

	static int i = 1;
	String callerID;
	String fromNumber;
	Platform platform;

	String toNumber;

	public RingOut(Platform platform, String fromNumber, String toNumber,
			String callerID, int counter) {
		this.platform = platform;
		this.fromNumber = fromNumber;
		this.toNumber = toNumber;
		if (callerID == null || callerID.equals(""))
			this.callerID = "";
		else
			this.callerID = callerID;
	}

	JSONObject createJsonNumberPair(String number) {
		JSONObject pair = new JSONObject();
		pair.put("phoneNumber", number);
		return pair;
	}

	public String createRingOut() throws Exception {

		JSONObject jbody = jRingOutBody();
		RequestBody body = RequestBody.create(
				Platform.ContentTypeSelection.JSON_TYPE_MARKDOWN.value, jbody
						.toString().getBytes());
		APIResponse response = platform.sendRequest("post",
				"/restapi/v1.0/account/~/extension/~/ringout", body, null);
		System.out.println("\nMaking a Ringout (" + i + ")...");
		if (response.ok() == true) {
			JSONObject jResJsonObject = new JSONObject(response.body().string());
			String ringoutID = jResJsonObject.get("id").toString();
			return ringoutID;
		} else {
			throw new Exception("Ringout not working as Expected!!");
		}
	}

	public void deleteRingOut(String ringOutId) throws Exception {
		APIResponse response = platform.sendRequest("delete",
				"/restapi/v1.0/account/~/extension/~/ringout/" + ringOutId,
				null, null);
		if (response.ok() == true) {
			System.out.println("\nDisconnecting the call (" + i++ + ")...");
		} else {
			throw new Exception("Ringout deleting not working as Expected!!");
		}
	}

	JSONObject jRingOutBody() {

		JSONObject jbody = new JSONObject();
		jbody.put("to", createJsonNumberPair(fromNumber));
		jbody.put("from", createJsonNumberPair(toNumber));
		jbody.put("callerId", createJsonNumberPair(callerID));
		jbody.put("playPrompt", "false");
		return jbody;
	}
}
