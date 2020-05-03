package com.randomsturvs.collaboux.config;

import com.randomsturvs.collaboux.models.KafkaTopic;
import com.randomsturvs.collaboux.properties.KafkaProperties;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

@EnableConfigurationProperties({KafkaProperties.class})
@Configuration
public class BootstrapConfig implements InitializingBean {

    @Autowired
    private KafkaProperties properties;

    @Override
    public void afterPropertiesSet() throws Exception, ExecutionException {
        List<KafkaTopic>  kafkaTopics =  getKafkaTopics();
        kafkaTopics.stream().forEach(kafkaTopic -> {
            Properties config = new Properties();
            config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getOptions().get("brokers"));
            config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG,properties.getOptions().get("requestTimeoutMs"));

            AdminClient admin = AdminClient.create(config);
            ListTopicsResult listTopics = admin.listTopics();
            Set<String> names = null;
            try {
                names = listTopics.names().get();
                boolean contains = names.contains(kafkaTopic.getTopic());
                if (!contains) {
                    List<NewTopic> topicList = new ArrayList();
                    NewTopic newTopic = new NewTopic(kafkaTopic.getTopic(), kafkaTopic.getPartition(), Short.valueOf(kafkaTopic.getReplicationFactor().toString())).configs(null);
                    topicList.add(newTopic);
                    admin.createTopics(topicList);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public List<KafkaTopic> getKafkaTopics() {
        JSONParser jsonParser = new JSONParser(0);

        URL resource = getClass().getClassLoader().getResource(properties.getBootstrapFilePath());

        List<KafkaTopic> topics = new ArrayList<>();
        if (resource != null){
            try (FileReader reader = new FileReader(resource.getFile()))
            {
                Object obj = jsonParser.parse(reader);
                JSONArray topicsList = (JSONArray) obj;

                topicsList.forEach(jsonObject -> {
                    topics.add(KafkaTopic.fromJSON((JSONObject) ((JSONObject) jsonObject).get("topic")));
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return topics;
    }

}
