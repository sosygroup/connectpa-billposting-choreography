package it.univaq.connectpa.publicbillposting.sia.endpoints.manager.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SetInvocationAddressUtils {

	// <calling participant name , <participant name, participant address>>
	private static Map<String, Map<String, String>> participantsArtifactsEndpointsData = new ConcurrentHashMap<>();

	public static void storeParticipantArtifactEndpointData(String storingParticipantName, String participantName, String participantEndpoint){

		if(participantsArtifactsEndpointsData.containsKey(storingParticipantName)){
			participantsArtifactsEndpointsData.get(storingParticipantName).put(participantName, participantEndpoint);
		}
		else{
			Map<String, String> callingParticipantEndpointsData = new ConcurrentHashMap<>();
			callingParticipantEndpointsData.put(participantName, participantEndpoint);
			participantsArtifactsEndpointsData.put(storingParticipantName, callingParticipantEndpointsData);
		}
	}

	public static String getParticipantArtifactEndpointAddress(String callingParticipantName, String participantName){

		if(participantsArtifactsEndpointsData.containsKey(callingParticipantName) && participantsArtifactsEndpointsData.get(callingParticipantName).containsKey(participantName)){
			return participantsArtifactsEndpointsData.get(callingParticipantName).get(participantName);
		}
		return null;
	}

}

