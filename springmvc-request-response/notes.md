# Request

## Parameters

```
    // query : http://localhost:8080/user?name=111, 可以在函数参数处获得name的值
    // or post request
    @RequestMapping(value = "/user")
    @ResponseBody
    public String query(String name, int age) {
        System.out.println("parameter: name =  " + name +" age ="+age);
        return "{'module':'user delete'}";
    }

    //可以用@RequestParam("name")来指定request中的参数注入到哪一个形参
    @RequestMapping(value = "/differentParam")
    @ResponseBody
    public String query2(@RequestParam("name") String username, int age) {
        System.out.println("parameter: name =  " + username +" age ="+age);
        return "{'module':'user delete'}";
    }


    //如果request 参数中有对象的attribute，会直接绑定
    //http://localhost:8080/differentParam?name=xiao&age=11
    //User{age: 11, name:'xiao'}
    @RequestMapping(value = "/pojoParam")
    @ResponseBody
    public String query2(User user) {
        System.out.println("parameter: name =  " + user.getName() +" age ="+user.getAge());
        return "{'module':'pojo user get'}";
    }

    //array
    //http://localhost:8080/arrayParam?likes=111&likes=222&likes=333
    @RequestMapping(value = "/arrayParam")
    @ResponseBody
    public String query2(String [] likes) {
        System.out.println(Arrays.toString(likes));
        return "{'module':'array user get'}";
    }

    //List
    @RequestMapping(value = "/listParam")
    @ResponseBody
    public String query2(@RequestParam List<String> likes) {
        System.out.println(likes);
        return "{'module':'list user get'}";
    }
```

## json

### Dependency
```
<dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.14.1</version>
</dependency>
```

### Get Json in Request Body
Json object is in the request body. So we should we @RequestBody to bind with parameters.
```
@RequestMapping(value = "/jsonParam")
    @ResponseBody
    public String jsonRequest(@RequestBody User user) {
        System.out.println("parameter: name =  " + user.getName() +" age ="+user.getAge());
        return "{'module':'pojo user get'}";
    }
```


### Date Format Parameter
```
@RequestMapping(value = "/dateParam")
    @ResponseBody
    public String dataRequest(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        System.out.println("parameter: date = "+ date);
        return "{'module':'date get'}";
    }
```

# Response

## Jump to Page

### Dependency
```
<dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
```

### Controller

1. return type is string
2. return value is the page name
```
    @RequestMapping("/toPage")
    public String toPage(){
        System.out.println("jump to index.jsp");
        return "index.jsp";
    }
```

## Return text
```
    @RequestMapping("/toText")
    @ResponseBody
    public String toTest(){
        System.out.println("return text");
        return "index";
    }
```

## Return POJO Object
```
    @RequestMapping("/toPOJO")
    @ResponseBody
    public User toPOJP(){
        System.out.println("return POJO");
        User user = new User();
        user.setAge(11);
        user.setName("111");
        return user;    
    }
```