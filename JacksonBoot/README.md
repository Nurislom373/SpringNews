# Jackson Library

Jackson Librarysi JSON yoki XML filelardan ma'lumotlarni o'qish va filelarga JSON yoki XML formatda yozish uchun
ishlatiladigan Library yani kutubxonalaridan biri.

# ObjectMapper

It has built in Object Mapper class which parses json files and deserializes it to custom java objects. It helps in
generating json from java objects.

<hr/>

ObjectMapper classi objectlarni json filelarga parse qilish va ushbu ma'lumotlarni java objectlari uchun deserialize
qilish uchun ishlatiladi. Ushbu class Java Objectlaridan Json yaratish uchun yordam beradi.

## Dependencies

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
</dependency>
```

# Reading and Writing Using ObjectMapper

The simple readValue API of the ObjectMapper is a good entry point. We can use it to parse or deserialize JSON content
into a Java object.

Also, on the writing side, we can use the writeValue API to serialize any Java object as JSON output.

<hr/>

Biz objectMapper-ni readValue method orqali JSON Java object aylantirish uchun boshqacha qilib aytadigan bo'lsak
Deserialize qilish uchun ishlatamiz.

writeValue methoddini esa biz Java Objectni JSON aylantirish ya'ni Serialize qilishimiz uchun ishlatamiz.

```java
public class Car {

    private String color;
    private String type;

    // standard getters setters
}
```

### Java Object to JSON

```java
ObjectMapper objectMapper = new ObjectMapper();
Car car = new Car("yellow","renault");
objectMapper.writeValue(new File("target/car.json"),car);
```

```java
{"color":"yellow","type":"renault"}
```

The methods writeValueAsString and writeValueAsBytes of ObjectMapper class generate a JSON from a Java object and return
the generated JSON as a string or as a byte array:

<hr/>

ObjectMapper-ni classning writeValueAsString va writeValueAsBytes methodlari Java Objectidan JSON hosil qiladi va json
ni string yoki byte array sifatida qaytaradi.

```java
String carAsString=objectMapper.writeValueAsString(car);
```

### JSON to Java Object

```java
String json="{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
Car car = objectMapper.readValue(json,Car.class);    
```

```java
Car car = objectMapper.readValue(new File("src/test/resources/json_car.json"),Car.class);
```

## JSON to Jackson JsonNode

Alternatively, a JSON can be parsed into a JsonNode object and used to retrieve data from a specific node:

```java
String json="{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
JsonNode jsonNode = objectMapper.readTree(json); 
String color = jsonNode.get("color").asText();
// Output: color -> Black
```

## Creating a Java List From a JSON Array String

We can parse a JSON in the form of an array into a Java object list using a TypeReference:

```java
String jsonCarArray="[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
List<Car> listCar=objectMapper.readValue(jsonCarArray,new TypeReference<List<Car>>(){});
```

## Creating Java Map From JSON String

Similarly, we can parse a JSON into a Java Map:

```java
String json="{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
Map<String, Object> map=objectMapper.readValue(json,new TypeReference<Map<String, Object>>(){});
```

# Advanced Features

One of the greatest strengths of the Jackson library is the highly customizable serialization and deserialization
process.

<hr/>

Jackson Librarysining eng kuchli xususiyatlaridan biri Serialize va Deserialize qilish jarayoni customize qilishimiz
mumkin.

## Configuring Serialization or Deserialization Feature

While converting JSON objects to Java classes, in case the JSON string has some new fields, the default process will
result in an exception. Through the configure method, we can extend the default process to ignore the new fields:

<hr/>

Agar biz JSON objectlarni Java objectlarga Deserialize qilyotgan paytimizda, agar JSON da yangi fieldlar bo'lsa
exception tashlaydi. _UnrecognizedPropertyException_

```java
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
Car car = objectMapper.readValue(jsonString,Car.class);

JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
JsonNode jsonNodeYear = jsonNodeRoot.get("year");
String year = jsonNodeYear.asText();
```

Yet another option is based on the FAIL_ON_NULL_FOR_PRIMITIVES, which defines if the null values for primitive values
are allowed:

```java
objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,false);
```

Similarly, FAIL_ON_NUMBERS_FOR_ENUM controls if enum values are allowed to be serialized/deserialized as numbers:

```java
objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS,false);
```

# Creating Custom Serializer or Deserializer

Another essential feature of the ObjectMapper class is the ability to register a custom serializer and deserializer.

Custom serializers and deserializers are very useful in situations where the input or the output JSON response is
different in structure than the Java class into which it must be serialized or deserialized.

Below is an example of a custom JSON serializer:

```java
public class CustomCarSerializer extends StdSerializer<Car> {
    
    public CustomCarSerializer() {
        this(null);
    }

    public CustomCarSerializer(Class<Car> t) {
        super(t);
    }

    @Override
    public void serialize(
      Car car, JsonGenerator jsonGenerator, SerializerProvider serializer) {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("car_brand", car.getType());
        jsonGenerator.writeEndObject();
    }
}
```

```java
ObjectMapper mapper = new ObjectMapper();
SimpleModule module = new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
module.addSerializer(Car.class, new CustomCarSerializer());
mapper.registerModule(module);
Car car = new Car("yellow", "renault");
String carJson = mapper.writeValueAsString(car);
```

```java
var carJson = {"car_brand":"renault"}
```

Custom Deserializer

```java
public class CustomCarDeserializer extends StdDeserializer<Car> {
    
    public CustomCarDeserializer() {
        this(null);
    }

    public CustomCarDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Car deserialize(JsonParser parser, DeserializationContext deserializer) {
        Car car = new Car();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        
        // try catch block
        JsonNode colorNode = node.get("color");
        String color = colorNode.asText();
        car.setColor(color);
        return car;
    }
}
```

```java
String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
ObjectMapper mapper = new ObjectMapper();
SimpleModule module = new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
module.addDeserializer(Car.class, new CustomCarDeserializer());
mapper.registerModule(module);
Car car = mapper.readValue(json, Car.class);
```