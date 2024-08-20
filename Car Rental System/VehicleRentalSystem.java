

import java.util.List;

public class VehicleRentalSystem {

    List<Store> storeList;
    List<User> userList;

    VehicleRentalSystem(List<Store> stores, List<User> users) {

        this.storeList = stores;
        this.userList = users;
    }


    public Store getStore(Location location){

      Store selectedStore= storeList.stream().filter((store)-> location.city.equals(store.getStoreLocation().city)).findFirst().orElse(null);
      return selectedStore;
    }



    //addUsers

    //remove users


    //add stores

    //remove stores



}
