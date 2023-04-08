# Spring Working YAML

Spring profiles help enable Spring Applications to define different properties for different environments.

Let's take a look at a simple YAML file that contains two profiles. The three dashes separating the two profiles 
indicate the start of a new document, so all the profiles can be described in the same YAML file.

The annotation used here are:

- `@Configuration` - this marks the class source of bean definitions
- `@ConfigurationProperties` - this binds and validates the external configurations to a configuration class
- `@EnableConfigurationProperties` - this annotation is used to enable @ConfigurationProperties annotated beans in the 
   Spring application