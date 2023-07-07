ConnectPA Public Billposting Choreography
=======================
This repository contains the replication package of the paper published in the journal [Journal of Software: Evolution and Process](https://onlinelibrary.wiley.com/journal/20477481) with the title **Synthesis of context‐aware business‐to‐business processes for location‐based services through choreographies**.

The paper is available at [https://doi.org/10.1002/smr.2416](https://doi.org/10.1002/smr.2416)

## Authors
This study has been designed, developed, and reported by the following investigators:
- [Gianluca Filippone](mailto:gianluca.filippone@graduate.univaq.it) (University of L'Aquila, Italy)
- Marco Autili (University of L'Aquila, Italy)
- Massimo Tivoli (University of L'Aquila, Italy)

For any information, interested researchers can contact us by writing an email to [gianluca.filippone@graduate.univaq.it](mailto:gianluca.filippone@graduate.univaq.it)

## Choreography specification

### BPMN2 Model
![choreography](</choreography/specification/Public Billposting Choreography specification.png>)

### Participant services
| Participant service            | Description                                                                                                                                                                                                                                                                                                                                          |
|--------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Mobile App                     | Client of the system. It interacts with the other participants for the request of posting spaces, the selection of the spaces, and the confirmation of the request. Moreover, it allows the user to pay for the bills required to affix posts.                                                                                                       |
| Poster Service                 | Prosumer service that includes some of the business logic of the system. As a provider, it receives requests from the Mobile APP to get the list of available spaces, the confirmation of the selected ones, and the billings for the payments. As a consumer, it communicates with the services of the municipalities and with the payment service. |
| Payment Service                | Prosumer service that offers the interfaces that are needed to receive the information for the payments and to pay them. As a consumer, it interacts with the municipalities in order to send the confirmation of the paid bills.                                                                                                                    |
| Cart                           | Provider service that exposes the interfaces needed to add, remove, and get all the elements of a virtual cart                                                                                                                                                                                                                                       |
| Municipality Poster Service(s) | Provider services, offered by the municipalities, that allow sending requests for public postings                                                                                                                                                                                                                                                    |

## Repository structure

```
connectpa-billposting-choreography
|   pom.xml 'build all the project'
|
|---caCDs 'WS-BPEL implementation of the context-aware coordination delegates'
|   |---caCDMobileApp
|   |---caCDPaymentService
|   └---caCDPosterService
|
|---choreography
|   |---specification
|   |       Public Billposting Choreography specification.png
|   |       Public Billposting Choreography - Payment Variant A.png
|   |       Public Billposting Choreography - Payment Variant B.png
|   |
|   └---architecture
|           Public Billposting System architecture.png
|
|---contextmanager 'Java implementation of the Context Manager'
|
|---monitor 'web application for the visualization of the results'
|
|---participant-services 'Java implementation of the participant services (Mobile app, providers and prosumers)
|   |   pom.xml 'build all the services'
|   |   
|   |---cart
|   |---mobileapp
|   |---municipalityposterservice
|   |---paymentservice
|   └---posterservice
|
|---serviceregistry 'Java implementation of the service registry'
|
|---sia-endpoints-client 'Java client application for the set invocation address (n.b., see documentation before using it)'
|
└---sia-endpoints-manager 'endpoints manager (n.b., see documentation before using it)'
```

## Installation

### Requirements
Java JDK 8 or later, Apache Maven 3

### Download and build

```
git clone https://github.com/sosygroup/connectpa-billposting-choreography.git connectpa-billposting-choreography
cd connectpa-billposting-choreography
mvn install
```

## Deployment and enactment

### Set up your target nodes
Install on your (virtual) machine(s) one or more servlet containers (e.g., Apache Tomcat). Be sure that each servlet container is accessibile from the network.

### Deploy provider and prosumer services
The folder ```participant-services``` contains the set of provider and prosumer services.
Deploy the ```.war``` files from the ```participant-services/war``` folder (they are generated after running ```mvn install``` from the root pom) in one or more servlet containers.

### Deploy Context-aware CDs
The folder ```caCDs``` contains the context-aware Coordination Delegates (caCDs). Deploy each caCD on an orchestrator engine for WS-BPEL like [Apache Ode](https://ode.apache.org).

Note: If using Apache Ode, Tomcat 8 or 8.5 is required. Support for newer Tomcat version is not guaranteed.

After that your orchestrator engine has been deployed, make sure to add the ```sia-endpoints-manager.jar``` file, contained in the ```sia-endpoints-manager/target``` folder, in the library of the orchestrator engine. If you are using Apache Ode, simply copy it into ```WEB-INF/lib/``` folder of your Apache Ode webapp deploymeny directory (e.g., ```[Apache Tomcat dir]/webapps/ode/WEB-INF/lib```).

### Deploy the Context Manager
The file ```contextmanager.war``` is generated into the root folder after running ```mvn clean install```. Deploy the service in a servlet container.

### Deploy the Service Registry
The service registry is packaged as a ```.jar``` file inside the ```serviceregistry/target``` folder. Copy the file on the selected (virtual) machine and run the ```mvn exec:java``` from within the directory containing the registry ```.jar``` file.

Update the ```server.port``` property into the ```src/main/resources/application.properties``` file to customize the port exposed by the service (9090 is set as default port). After the update, run the command ```mvn clean install``` from the ```serviceregistry/``` directory and run again.

### Deploy the Monitor service
The monitor service is packaged as a ```.jar``` file inside the ```monitor/target``` folder. Copy the file on the selected (virtual) machine and run the ```mvn exec:java``` from within the directory containing the monitor ```.jar``` file.

Update the ```server.port``` property into the ```src/main/resources/application.properties``` file to customize the port exposed by the service (8888 is set as default port).

Update the ```monitor.services.baseUrl``` properties to set the actual deployment locations of the provider and prosumer services (add more baseUrls for all the deployed instances of Municipality Poster Service, if needed).

After the update, run the command ```mvn clean install``` from the ```monitor/``` directory and run again.


## Execution and monitoring

### Set Invocation Address
In order to let the system know the deployment location of each service, it is needed to configure and run the application located in ```sia-endpoints-client``` folder.

After having deployed the providers, prosumers, Context Evaluator, CDs, and eCD, edit the ```endpoints.properties``` file contained into ```sia-endpoints-client/src/main/resources``` directory. It is sufficient to change the host address and the port of the servlet container of each service.

Execute mvn clean compile to apply changes and then ```mvn exec:java``` to run.

A default deployment schema is provided if you want to avoid to perform this step:

| Service                                                     | Default host:port |
|-------------------------------------------------------------|-------------------|
| All participant services (providers, prosumers, Mobile App) | localhost:9080    |
| Context-aware CDs                                           | localhost:8080    |
| Context Manager                                             | localhost:8090    |
| Service Registry                                            | localhost:9090    |
| Monitor service                                             | localhost:8888    |


Note: locations of caCDMobileApp and Service Registry are not updated in the Mobile App and Context Manager services. Update the references inside the ```MobileAppClient.java``` and ```ServiceRegistryClient.java``` files before deploying and running the application (running ```mvn clean install``` is required for the changes to take effect before re-deploying).

### Populate the Service Registry
Service registry offers REST APIs to get, add and remove instances of the Muncipality Poster Service.

To add an instance of the Municipality Poster Service, send a ```POST``` request to ```http://[host]:[port]/serviceregistry```. Example request payload:
```json
{
	"serviceName": "MunicipalityPosterService",
	"instanceName": "L'Aquila",
	"endpoint": "http://localhost:9080/municipalityposterservice/municipalityposterservice"
}
```

### Populate the Context Manager
Context Manager offers a REST API to dynamically add the static context information of the instances of Municipality Poster Service (i.e., reference latitude and reference longitude). They have be manually set before running the choreography.

To set the static context information of a Municipality Poster Service instance, send a ```POST``` request to ```http://[host]:[port]/contextmanager/municipalityPSInstances```. Example request payload:

```json
{
    "instanceName": "L'Aquila",
    "context": {
        "referenceLatitude": 42.3484,
        "referenceLongitude": 13.3991
    }
}
```

### Running the choreography
The participant that starts a new choreography instance is ```mobileapp```. It is developed as a webservice offering a hook endpoint to start the choreography.

Note: before starting the choreography, update the URL reference to the caCDMobileApp inside the ```MobileAppClient.java``` file with the actual deployment location of the caCDMobileApp, if needed. Run ```mvn clean install``` to apply changes and deploy the ```mobileapp.java``` file inside any servlet container.

Send a ```GET``` request to ```http://[host]:[port]/mobileapp/start/[instanceId]``` to start a new choreography instance with the selected ```instanceId```. If running multiple choreography instances at the same time (e.g., simulating many concurrent users), be careful to select different instance ids.

### View the monitor
The ```monitor``` service allows to check the timeline of all the choreography instances that have been executed.

After updating the participant service references and having deployed and run the service, access the dashboard at ```http://[host]:[port]/monitor/```


## License
Licensed under the Apache Software License, Version 2.0.
