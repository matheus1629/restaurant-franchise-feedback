package com.restaurant.feedbacksstorage.init;

import com.restaurant.feedbacksstorage.model.RestaurantEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeederRestaurant {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeederRestaurant.class);
    protected static List<RestaurantEntity> restaurantEntities = Arrays.asList(
            new RestaurantEntity(0, "SOUTHEAST", "São Paulo", "Campinas", "35235642"),
            new RestaurantEntity(1, "SOUTHEAST", "Rio de Janeiro", "Rio de Janeiro", "87905231"),
            new RestaurantEntity(2, "SOUTHEAST", "Minas Gerais", "Belo Horizonte", "46529870"),
            new RestaurantEntity(3, "SOUTH", "Rio Grande do Sul", "Porto Alegre", "74510386"),
            new RestaurantEntity(4, "SOUTH", "Paraná", "Curitiba", "23149875"),
            new RestaurantEntity(5, "SOUTHEAST", "São Paulo", "São Paulo", "58263091"),
            new RestaurantEntity(6, "SOUTHEAST", "Rio de Janeiro", "Niterói", "90674215"),
            new RestaurantEntity(7, "SOUTHEAST", "Espírito Santo", "Vitória", "18795643"),
            new RestaurantEntity(8, "SOUTHEAST", "Minas Gerais", "Uberlândia", "39521768"),
            new RestaurantEntity(9, "SOUTHEAST", "Rio de Janeiro", "Petrópolis", "65892134"),
            new RestaurantEntity(10, "NORTHEAST", "Bahia", "Salvador", "12385467"),
            new RestaurantEntity(11, "NORTHEAST", "Pernambuco", "Recife", "95423786"),
            new RestaurantEntity(12, "NORTHEAST", "Ceará", "Fortaleza", "64258971"),
            new RestaurantEntity(13, "NORTHEAST", "Maranhão", "São Luís", "87431029"),
            new RestaurantEntity(14, "NORTHEAST", "Paraíba", "João Pessoa", "51029384"),
            new RestaurantEntity(15, "MIDWEST", "Goiás", "Goiânia", "32659847"),
            new RestaurantEntity(16, "MIDWEST", "Mato Grosso", "Cuiabá", "97538461"),
            new RestaurantEntity(17, "MIDWEST", "Mato Grosso do Sul", "Campo Grande", "20387564"),
            new RestaurantEntity(18, "MIDWEST", "Distrito Federal", "Brasília", "43785921"),
            new RestaurantEntity(19, "MIDWEST", "Minas Gerais", "Uberaba", "86573194"),
            new RestaurantEntity(20, "NORTH", "Amazonas", "Manaus", "54983217"),
            new RestaurantEntity(21, "NORTH", "Pará", "Belém", "71359864"),
            new RestaurantEntity(22, "NORTH", "Rondônia", "Porto Velho", "28657493"),
            new RestaurantEntity(23, "SOUTHEAST", "Minas Gerais", "Belo Horizonte", "92184736"),
            new RestaurantEntity(24, "NORTH", "Acre", "Rio Branco", "63847219"),
            new RestaurantEntity(25, "SOUTH", "Santa Catarina", "Florianópolis", "57492013"),
            new RestaurantEntity(26, "SOUTHEAST", "São Paulo", "Santos", "19876543"),
            new RestaurantEntity(27, "NORTHEAST", "Bahia", "Porto Seguro", "45039286"),
            new RestaurantEntity(28, "MIDWEST", "Goiás", "Anápolis", "82673915"),
            new RestaurantEntity(29, "NORTH", "Tocantins", "Palmas", "30769482"),
            new RestaurantEntity(30, "SOUTH", "Paraná", "Londrina", "63254189"),
            new RestaurantEntity(31, "SOUTHEAST", "Rio de Janeiro", "Campos dos Goytacazes", "89543276"),
            new RestaurantEntity(32, "NORTHEAST", "Ceará", "Juazeiro do Norte", "12398765"),
            new RestaurantEntity(33, "SOUTHEAST", "São Paulo", "Campinas", "78954320"),
            new RestaurantEntity(34, "NORTH", "Roraima", "Boa Vista", "50671938"),
            new RestaurantEntity(35, "SOUTH", "Rio Grande do Sul", "Pelotas", "23456789"),
            new RestaurantEntity(36, "SOUTHEAST", "Minas Gerais", "Governador Valadares", "89765432"),
            new RestaurantEntity(37, "NORTHEAST", "Alagoas", "Maceió", "34567890"),
            new RestaurantEntity(38, "MIDWEST", "Distrito Federal", "Taguatinga", "67890123"),
            new RestaurantEntity(39, "SOUTHEAST", "Amazonas", "Parintins", "45678901"),
            new RestaurantEntity(40, "SOUTHEAST", "São Paulo", "Santos", "541258014"),
            new RestaurantEntity(41, "MIDWEST", "Distrito Federal", "Brasília", "45648001"),
            new RestaurantEntity(42, "SOUTH", "Paraná", "Curitiba", "63530410"),
            new RestaurantEntity(43, "NORTH", "Paraíba", "João Pessoa", "13460572"),
            new RestaurantEntity(44, "NORTHEAST", "Ceará", "Fortaleza", "25479050"),
            new RestaurantEntity(45, "NORTHEAST", "Rio de Janeiro", "Petrópolis", "05427004"),
            new RestaurantEntity(46, "NORTHEAST", "Maranhão", "São Luís", "96304584"),
            new RestaurantEntity(47, "NORTHEAST", "Bahia", "Salvador", "75408534"),
            new RestaurantEntity(48, "NORTHEAST", "Pernambuco", "Recife", "10042367"),
            new RestaurantEntity(49, "SOUTHEAST", "São Paulo", "São Paulo", "84505475")
    );


    @Bean
    CommandLineRunner initRestaurantsDocuments(MongoTemplate mongoTemplate) {
        return args -> {

            logger.info("Checking if seeders of the collection restaurants have already been inserted");
            if (mongoTemplate.getCollection("restaurants").countDocuments() == 0) {

                logger.info("Inserting seeders into restaurant collection");
                mongoTemplate.insertAll(restaurantEntities);
                logger.info("Insertion of seeders in restaurant collection completed");
            } else {
                logger.info("Seeders from the restaurants collection already inserted. No action required");
            }
        };
    }
}
