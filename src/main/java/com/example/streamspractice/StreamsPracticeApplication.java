package com.example.streamspractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StreamsPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamsPracticeApplication.class, args);




        List<Person> people = getPeople();

        // Imperative approach ❌

    /*
    List<Person> females = new ArrayList<>();
    for (Person person : people) {
      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }
    females.forEach(System.out::println);
    */

        // Declarative approach ✅

        // Filter by gender Female
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        //females.forEach(System.out::println);

        // Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                .collect(Collectors.toList());

//    sorted.forEach(System.out::println);

        // All match -si todos son mayores a la edad dada
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 8);

        System.out.println(allMatch);
        // Any match - si alguno es mayot a la edad dada
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 121);

//    System.out.println(anyMatch);
        // None match - si no existe devuelve true, si existe devuelve false
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));

        // System.out.println(noneMatch);

        // Max. Encuentra el mayor dada un atributo dado (edad)
        people.stream()
                .max(Comparator.comparing(Person::getAge));
//        .ifPresent(System.out::println);

        // Min Encuentra el menor dada un atributo dado (edad)
        people.stream()
                .min(Comparator.comparing(Person::getAge));
//        .ifPresent(System.out::println);

        // Group - agrupa los elementoros por cada categoria de genero
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });

        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE),
                new Person("Ernezto Zakueta", 120, Gender.NA)
        );
    }
    }


