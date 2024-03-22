package com.restaurant.feedbackscollector.init;

import com.restaurant.feedbackscollector.model.RestaurantEntity;
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

    @Bean
    CommandLineRunner init(MongoTemplate mongoTemplate) {
        return args -> {

            logger.info("Checking if seeders of the collection Restaurants have already been inserted");
            if (mongoTemplate.getCollection("restaurants").countDocuments() == 0) {

                List<RestaurantEntity> documents = Arrays.asList(
                        new RestaurantEntity(0, "southeast", "São Paulo", "Campinas", "35235642"),
                        new RestaurantEntity(1, "southeast", "Rio de Janeiro", "Rio de Janeiro", "87905231"),
                        new RestaurantEntity(2, "southeast", "Minas Gerais", "Belo Horizonte", "46529870"),
                        new RestaurantEntity(3, "south", "Rio Grande do Sul", "Porto Alegre", "74510386"),
                        new RestaurantEntity(4, "south", "Paraná", "Curitiba", "23149875"),
                        new RestaurantEntity(5, "southeast", "São Paulo", "São Paulo", "58263091"),
                        new RestaurantEntity(6, "southeast", "Rio de Janeiro", "Niterói", "90674215"),
                        new RestaurantEntity(7, "southeast", "Espírito Santo", "Vitória", "18795643"),
                        new RestaurantEntity(8, "southeast", "Minas Gerais", "Uberlândia", "39521768"),
                        new RestaurantEntity(9, "southeast", "Rio de Janeiro", "Petrópolis", "65892134"),
                        new RestaurantEntity(10, "northeast", "Bahia", "Salvador", "12385467"),
                        new RestaurantEntity(11, "northeast", "Pernambuco", "Recife", "95423786"),
                        new RestaurantEntity(12, "northeast", "Ceará", "Fortaleza", "64258971"),
                        new RestaurantEntity(13, "northeast", "Maranhão", "São Luís", "87431029"),
                        new RestaurantEntity(14, "northeast", "Paraíba", "João Pessoa", "51029384"),
                        new RestaurantEntity(15, "midwest", "Goiás", "Goiânia", "32659847"),
                        new RestaurantEntity(16, "midwest", "Mato Grosso", "Cuiabá", "97538461"),
                        new RestaurantEntity(17, "midwest", "Mato Grosso do Sul", "Campo Grande", "20387564"),
                        new RestaurantEntity(18, "midwest", "Distrito Federal", "Brasília", "43785921"),
                        new RestaurantEntity(19, "midwest", "Minas Gerais", "Uberaba", "86573194"),
                        new RestaurantEntity(20, "north", "Amazonas", "Manaus", "54983217"),
                        new RestaurantEntity(21, "north", "Pará", "Belém", "71359864"),
                        new RestaurantEntity(22, "north", "Rondônia", "Porto Velho", "28657493"),
                        new RestaurantEntity(23, "southeast", "Minas Gerais", "Belo Horizonte", "92184736"),
                        new RestaurantEntity(24, "north", "Acre", "Rio Branco", "63847219"),
                        new RestaurantEntity(25, "south", "Santa Catarina", "Florianópolis", "57492013"),
                        new RestaurantEntity(26, "southeast", "São Paulo", "Santos", "19876543"),
                        new RestaurantEntity(27, "northeast", "Bahia", "Porto Seguro", "45039286"),
                        new RestaurantEntity(28, "midwest", "Goiás", "Anápolis", "82673915"),
                        new RestaurantEntity(29, "north", "Tocantins", "Palmas", "30769482"),
                        new RestaurantEntity(30, "south", "Paraná", "Londrina", "63254189"),
                        new RestaurantEntity(31, "southeast", "Rio de Janeiro", "Campos dos Goytacazes", "89543276"),
                        new RestaurantEntity(32, "northeast", "Ceará", "Juazeiro do Norte", "12398765"),
                        new RestaurantEntity(33, "southeast", "São Paulo", "Campinas", "78954320"),
                        new RestaurantEntity(34, "north", "Roraima", "Boa Vista", "50671938"),
                        new RestaurantEntity(35, "south", "Rio Grande do Sul", "Pelotas", "23456789"),
                        new RestaurantEntity(36, "southeast", "Minas Gerais", "Governador Valadares", "89765432"),
                        new RestaurantEntity(37, "northeast", "Alagoas", "Maceió", "34567890"),
                        new RestaurantEntity(38, "midwest", "Distrito Federal", "Taguatinga", "67890123"),
                        new RestaurantEntity(39, "southeast", "Amazonas", "Parintins", "45678901"),
                        new RestaurantEntity(40, "southeast", "São Paulo", "Santos", "541258014"),
                        new RestaurantEntity(41, "midwest", "Distrito Federal", "Brasília", "45648001"),
                        new RestaurantEntity(42, "south", "Paraná", "Curitiba", "63530410"),
                        new RestaurantEntity(43, "north", "Paraíba", "João Pessoa", "13460572"),
                        new RestaurantEntity(44, "northeast", "Ceará", "Fortaleza", "25479050"),
                        new RestaurantEntity(45, "northeast", "Rio de Janeiro", "Petrópolis", "05427004"),
                        new RestaurantEntity(46, "northeast", "Maranhão", "São Luís", "96304584"),
                        new RestaurantEntity(47, "northeast", "Bahia", "Salvador", "75408534"),
                        new RestaurantEntity(48, "northeast", "Pernambuco", "Recife", "10042367"),
                        new RestaurantEntity(49, "southeast", "São Paulo", "São Paulo", "84505475")

                );

                logger.info("Inserting seeders into Restaurant collection");
                mongoTemplate.insertAll(documents);
                logger.info("Insertion of seeders in Restaurant collection completed");
            } else {
                logger.info("Seeders from the 'restaurants' collection already inserted. No action required");
            }
        };
    }
}
