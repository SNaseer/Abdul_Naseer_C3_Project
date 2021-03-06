import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
      @BeforeEach
      public void setup(){
          LocalTime openingTime = LocalTime.parse("10:30:00");
          LocalTime closingTime = LocalTime.parse("22:00:00");
          restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
          restaurant.addToMenu("Sweet corn soup",119);
          restaurant.addToMenu("Vegetable lasagne", 269);
      }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
       boolean isRestaurantOpen= restaurant.isRestaurantOpen();
       assertTrue(isRestaurantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant spiedRestaurant= Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.isRestaurantOpen()).thenReturn(false);
        boolean isRestaurantOpen= spiedRestaurant.isRestaurantOpen();
        assertFalse(isRestaurantOpen);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    @Test
    public void get_total_item_cost_from_menu_should_sum_items_with_items_selected(){
          List<String> itemList=new ArrayList<>();
          itemList.add("Vegetable lasagne");
        itemList.add("Sweet corn soup");
          int totalCost=restaurant.getTotalItemsCost(itemList);
          assertEquals(388,totalCost);
    }
    @Test
    public void get_total_item_cost_from_menu_should_sum_items_without_items_selected(){
        List<String> itemList=new ArrayList<>();
        int totalCost=restaurant.getTotalItemsCost(itemList);
        assertEquals(0,totalCost);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}