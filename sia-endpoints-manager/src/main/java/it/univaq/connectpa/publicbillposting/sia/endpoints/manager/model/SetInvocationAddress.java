package it.univaq.connectpa.publicbillposting.sia.endpoints.manager.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setInvocationAddress", propOrder = {
    "participantName",
    "address"
})
public class SetInvocationAddress {

    protected String participantName;
    protected String address;

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String value) {
        this.participantName = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

}
