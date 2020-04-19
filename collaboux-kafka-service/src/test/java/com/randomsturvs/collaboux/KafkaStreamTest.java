package com.randomsturvs.collaboux;


import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnitRunner;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class KafkaStreamTest {


    public interface IKafkaConstants {
        public static String KAFKA_BROKERS = "localhost:9092";
        public static Integer MESSAGE_COUNT=1000;
        public static String CLIENT_ID="bloverse-notification-service";
        public static String GROUP_ID_CONFIG="consumerGroup1";
        public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
        public static String OFFSET_RESET_LATEST="latest";
        public static String OFFSET_RESET_EARLIER="earliest";
        public static Integer MAX_POLL_RECORDS=1;
    }

    public static Producer<String, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        return new KafkaProducer<String, String>(props);
    }


    private static class CustomSerializer implements Serializer<Object> {
        @Override
        public void configure(Map<String, ?> configs, boolean isKey) {
        }
        @Override
        public byte[] serialize(String topic, Object data) {
            byte[] retVal = null;
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                retVal = objectMapper.writeValueAsString(data).getBytes();
            } catch (Exception exception) {
                System.out.println("Error in serializing object"+ data);
            }
            return retVal;
        }
        @Override
        public void close() {

        }
    }

    @Test
    public void createTopics() throws IOException {

    }

    @Data
    private class ArticleMessage{
        private String Id= "article-628e6910-6ea9-11ea-92b2-978eeb042958";
        private String UserId="user-628e6910-6ea9-11ea-92b2-978eeb042958";
        private String Title="Slow Rkelly";
        private String Body="Cookie jah";
        private String richBody="<p>Cookie jar</>";
        private String Slug="cookie-jar";
        private Integer Country = 12;
        private Integer Category = 12;
        private String[] Keypoints = new String[]{"point1", "point2", "point3"};
        private String[] Keywords = new String[]{"point", "point", "point"};
        private String ImageUrl = "https://res.cloudinary.com/";
    }

    @Data
    private class UserMessage{
        private String id= "user.id",
        firstName= "user.firstName",
        lastName= "user.lastName",
        email= "user.email",
        dateOfBirth= "user.dateOfBirth",
        gender= "user.gender",
        phoneNumber= "user.phoneNumber",
        location= "user.location",
        userName= "user.userName",
        imageUrl= "user.imageUrl",
        bio= "user.bio";
    }

    @Data
    private class NotificationMessage{

        String[] users= new String[]{"23ade-0b7b-4e59-92f3-a19bef77e0a7","23ade-0b7b-4e59-92f3-a19bef77e0a7"};
        private String userId= "23ade-0b7b-4e59-92f3-a19bef77e0a7",
                type= "COMMENT", //'COMMENT' OR 'DISCUSSION'
                interactionId= "8c9d6168-0b51-473e-bf50-54649f2b1477",
                text= "Text of the discussion";
    }

    @Test
    public void testProducer() throws Exception{

        ObjectMapper Obj = new ObjectMapper();

        String jsonStr1 = Obj.writeValueAsString(new ArticleMessage());
        String jsonStr2 = Obj.writeValueAsString(new UserMessage());


        ProducerRecord producerRecord1 = new ProducerRecord<>("article_deleted", jsonStr1);
        ProducerRecord producerRecord2 = new ProducerRecord<>("user_upserted", jsonStr2);

        createProducer().send(producerRecord1).get();
        Object metadata = createProducer().send(producerRecord2).get();


        System.out.printf(jsonStr1);
        System.out.printf(jsonStr2);
    }

    @Test
    public void testArticleNotification() throws Exception{

        ObjectMapper Obj = new ObjectMapper();

        ProducerRecord producerRecord1 = new ProducerRecord<>(
                "USER_LOGIN","8c9d6168-0b51-473e-bf50-54649f2b147",
                Obj.writeValueAsString(new NotificationMessage()));

        createProducer().send(producerRecord1).get();
    }

    @Test
    public void testPartitioners(){
        List<String> strings = new ArrayList<>();
        strings.add("Maison");
        strings.add("John");

        strings.stream().collect(Collectors.partitioningBy(element -> element == "Maison"));
    }


    @Data
    private class Human{
        String name;
    }

    @Test
    public void testStream() throws InterruptedException  {

        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-starter-app");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> interactions = builder.stream("USER_CREATED");

        KafkaStreams streams = new KafkaStreams(builder.build(), config);

        final KTable<Windowed<String>, Long> anomalousUsers = interactions
                // map the user name as key, because the subsequent counting is performed based on the key
                .map((ignoredKey, messae) -> {
                    System.out.printf("New message via stream : %s -  %s", ignoredKey, messae);
                    return new KeyValue<>(messae, messae);
                })
                // count users, using one-minute tumbling windows;
                // no need to specify explicit serdes because the resulting key and value types match our default serde settings
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1).toMillis()))
                .count()
                // get users whose one-minute count is >= 3
                .filter((windowedUserId, count) -> count >= 3);

        streams.start();

        // print the topology
        System.out.println(streams.toString());

        Thread.sleep(200000);
        // shutdown hook to correctly close the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));


    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface BuilderProperty {

    }

    public class Person {
        private int age;
        private String name;

        @BuilderProperty
        public void setAge(int age) {
            this.age = age;
        }

        @BuilderProperty
        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    @SupportedAnnotationTypes("com.randomsturvs.collaboux.*")
    @SupportedSourceVersion(SourceVersion.RELEASE_8)
    public class BuilderProcessor extends AbstractProcessor {

        @Override
        public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
            for (TypeElement annotation : annotations) {
                Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

                Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream()
                        .collect(Collectors.partitioningBy(element ->
                                ((ExecutableType) element.asType()).getParameterTypes().size() == 1
                                        && element.getSimpleName().toString().startsWith("set")));

                List<Element> setters = annotatedMethods.get(true);
                List<Element> otherMethods = annotatedMethods.get(false);

                otherMethods.forEach(element ->
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                                "@BuilderProperty must be applied to a setXxx method "
                                        + "with a single argument", element));

                if (setters.isEmpty()) {
                    continue;
                }

                String className = ((TypeElement) setters.get(0)
                        .getEnclosingElement()).getQualifiedName().toString();

                Map<String, String> setterMap = setters.stream().collect(Collectors.toMap(
                        setter -> setter.getSimpleName().toString(),
                        setter -> ((ExecutableType) setter.asType())
                                .getParameterTypes().get(0).toString()
                ));

            }

            return true;
        }
    }

}
