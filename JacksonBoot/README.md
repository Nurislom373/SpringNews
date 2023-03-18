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

## Handling Date Formats 

The default serialization of java.util.Date produces a number, i.e., epoch timestamp (number of milliseconds since 
January 1, 1970, UTC). But this is not very human readable and requires further conversion to be displayed in a 
human-readable format.

```java
public class Request {
    private String carName;
    private Date datePurchased;

    // standard getters setters
}
```

To control the String format of a date and set it to, e.g., yyyy-MM-dd HH:mm a z

```java
ObjectMapper objectMapper = new ObjectMapper();
DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
objectMapper.setDateFormat(df);
String carAsString = objectMapper.writeValueAsString(request);
// output: {"car":{"color":"yellow","type":"renault"},"datePurchased":"2016-07-03 11:43 AM CEST"}
```

## Handling Collections

Another small but useful feature available through the DeserializationFeature class is the ability to generate the type 
of collection we want from a JSON Array response.

For example, we can generate the result as an array

```java
String jsonCarArray = 
  "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
Car[] cars = objectMapper.readValue(jsonCarArray, Car[].class);
```

or List

```java
String jsonCarArray = 
  "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
ObjectMapper objectMapper = new ObjectMapper();
List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
```

# Ignore Null Fields on the Class

Jackson allow us to control this behavior at either the class level:

```java
@JsonInclude(Include.NON_NULL)
public class MyDto { ... }
```

Or with more granularity at the field level:

```java
public class MyDto {

    @JsonInclude(Include.NON_NULL)
    private String stringValue;

    private int intValue;

    // standard getters and setters
}
```

# Ignore Null Fields Globally

Jackson also allows us to configure this behavior globally on the ObjectMapper:

```java
mapper.setSerializationInclusion(Include.NON_NULL);
```

Now any null field in any class serialized through this mapper is going to be ignored

```java
ObjectMapper mapper = new ObjectMapper();
mapper.setSerializationInclusion(Include.NON_NULL);
MyDto dtoObject = new MyDto();

String dtoAsString = mapper.writeValueAsString(dtoObject);

assertThat(dtoAsString, containsString("intValue"));
assertThat(dtoAsString, containsString("booleanValue"));
assertThat(dtoAsString, not(containsString("stringValue")));
```

Ignoring null fields is such a common Jackson configuration because it's often the case that we need to have better 
control over the JSON output.

# Change Name of Field for Serialization

```java
public class MyDto {
    private String stringValue;

    public MyDto() {
        super();
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
```

Serializing it will result in the following JSON

```json
{"stringValue":"some value"}
```

To customize that output so that, instead of stringValue we get – for example – strVal, we need to simply annotate 
the getter

```java
@JsonProperty("strVal")
public String getStringValue() {
    return stringValue;
}
```

Now, on serialization, we will get the desired output

```java
{"strVal":"some value"}
```

```java
ObjectMapper mapper = new ObjectMapper();
MyDtoFieldNameChanged dtoObject = new MyDtoFieldNameChanged();
dtoObject.setStringValue("a");

String dtoAsString = mapper.writeValueAsString(dtoObject);

assertThat(dtoAsString, not(containsString("stringValue")));
assertThat(dtoAsString, containsString("strVal"));
```

# A Public Field

The simplest way to make sure a field is both serializable and deserializable is to make it public.

```java
public class MyDtoAccessLevel {
    private String stringValue;
    int intValue;
    protected float floatValue;
    public boolean booleanValue;
    // NO setters or getters
}
```

Out of the four fields of the class, just the public booleanValue will be serialized to JSON by default

```java
ObjectMapper mapper = new ObjectMapper();
MyDtoAccessLevel dtoObject = new MyDtoAccessLevel();

String dtoAsString = mapper.writeValueAsString(dtoObject);
assertThat(dtoAsString, not(containsString("stringValue")));
assertThat(dtoAsString, not(containsString("intValue")));
assertThat(dtoAsString, not(containsString("floatValue")));

assertThat(dtoAsString, containsString("booleanValue"));
```

## A Getter Makes a Non-Public Field Serializable and Deserializable

Now, another simple way to make a field – especially a non-public field – serializable, is to add a getter for it

```java
public class MyDtoWithGetter {
    private String stringValue;
    private int intValue;

    public String getStringValue() {
        return stringValue;
    }
}
```

We now expect the stringValue field to be serializable, while the other private field not to be, as it has no getter

```java
@Test
public void givenDifferentAccessLevels_whenGetterAdded_thenSerializable() 
  throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    MyDtoGetter dtoObject = new MyDtoGetter();

    String dtoAsString = mapper.writeValueAsString(dtoObject);
    assertThat(dtoAsString, containsString("stringValue"));
    assertThat(dtoAsString, not(containsString("intValue")));
}
```

Unintuitively, the getter also makes the private field deserializable as well – because once it has a getter, the 
field is considered a property.

## A Setter Makes a Non-Public Field Deserializable Only

We saw how the getter made the private field both serializable and deserializable. On the other hand, a setter will only
mark the non-public field as deserializable

```java
public class MyDtoWithSetter {
    private int intValue;

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public int accessIntValue() {
        return intValue;
    }
}
```

As you can see, the private intValue field only has a setter this time. We do have a way to access the value, but that's
not a standard getter.

The unmarshalling process for intValue should work correctly

```java
@Test
public void givenDifferentAccessLevels_whenSetterAdded_thenDeserializable() 
  throws JsonProcessingException, JsonMappingException, IOException {
    String jsonAsString = "{\"intValue\":1}";
    ObjectMapper mapper = new ObjectMapper();

    MyDtoSetter dtoObject = mapper.readValue(jsonAsString, MyDtoSetter.class);

    assertThat(dtoObject.anotherGetIntValue(), equalTo(1));
}
```

And as we mentioned, the setter should only make the field deserializable, but not serializable:

```java
@Test
public void givenDifferentAccessLevels_whenSetterAdded_thenStillNotSerializable() 
  throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    MyDtoSetter dtoObject = new MyDtoSetter();

    String dtoAsString = mapper.writeValueAsString(dtoObject);
    assertThat(dtoAsString, not(containsString("intValue")));
}
```

# Make All Fields Globally Serializable

In some cases where, for example, you might not actually be able to modify the source code directly – we need to 
configure the way Jackson deals with non-public fields from the outside.

That kind of global configuration can be done at the ObjectMapper level, by turning on the AutoDetect function to use 
either public fields or getter/setter methods for serialization, or maybe turn on serialization for all fields

```java
ObjectMapper mapper = new ObjectMapper();
mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
```

The following test case verifies all member fields (including non-public) of MyDtoAccessLevel are serializable

```java
@Test
public void givenDifferentAccessLevels_whenSetVisibility_thenSerializable() 
  throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    MyDtoAccessLevel dtoObject = new MyDtoAccessLevel();

    String dtoAsString = mapper.writeValueAsString(dtoObject);
    assertThat(dtoAsString, containsString("stringValue"));
    assertThat(dtoAsString, containsString("intValue"));
    assertThat(dtoAsString, containsString("booleanValue"));
}
```

# Ignore a Field on Serialization or Deserialization

The following example shows a User object which contains sensitive password information which shouldn't be serialized to
JSON.

To get there, we simply add the @JsonIgnore annotation on the getter of the password, and enable deserialization for the
field by applying the @JsonProperty annotation on the setter

```java
@JsonIgnore
public String getPassword() {
    return password;
}
@JsonProperty
public void setPassword(String password) {
    this.password = password;
}
```

Now the password information won’t be serialized to JSON

```java
@Test
public void givenFieldTypeIsIgnoredOnlyAtSerialization_whenUserIsSerialized_thenIgnored() 
  throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    User userObject = new User();
    userObject.setPassword("thePassword");

    String userAsString = mapper.writeValueAsString(userObject);
    assertThat(userAsString, not(containsString("password")));
    assertThat(userAsString, not(containsString("thePassword")));
}
```

the JSON containing the password will be successfully deserialized to the User object

```java
@Test
public void givenFieldTypeIsIgnoredOnlyAtSerialization_whenUserIsDeserialized_thenCorrect() 
  throws JsonParseException, JsonMappingException, IOException {
    String jsonAsString = "{\"password\":\"thePassword\"}";
    ObjectMapper mapper = new ObjectMapper();

    User userObject = mapper.readValue(jsonAsString, User.class);

    assertThat(userObject.getPassword(), equalTo("thePassword"));
}
```