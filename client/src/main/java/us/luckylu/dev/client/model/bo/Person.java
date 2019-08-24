package us.luckylu.dev.client.model.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lu
 * @create 2019-03-26 9:49
 */
@Data
public class Person implements Serializable {

    /**
     * _id : 5d6098b6f495064bb009e223
     * index : 0
     * guid : b606fa32-ed98-4bc9-ae57-8d69e52a8d18
     * isActive : true
     * balance : $3,442.49
     * picture : http://placehold.it/32x32
     * age : 38
     * eyeColor : blue
     * name : {"first":"Thornton","last":"Hendrix"}
     * company : HARMONEY
     * email : thornton.hendrix@harmoney.info
     * phone : +1 (867) 553-2643
     * address : 399 Lois Avenue, Smeltertown, Puerto Rico, 5639
     * about : Sit quis aute cillum exercitation mollit duis voluptate. Exercitation aliquip sint magna qui nisi reprehenderit non est est. Ipsum elit minim commodo laboris fugiat voluptate in deserunt adipisicing nisi laborum excepteur ipsum. Nulla qui dolore exercitation duis irure veniam nostrud voluptate veniam. Consequat dolor nulla nostrud exercitation nisi ea. Enim dolor Lorem culpa exercitation ipsum laborum magna deserunt.
     * registered : Friday, September 9, 2016 3:55 AM
     * latitude : 79.212804
     * longitude : 140.931356
     * tags : ["non","excepteur","eu","cillum","ut"]
     * range : [0,1,2,3,4,5,6,7,8,9]
     * friends : [{"id":0,"name":"Rebecca Rodgers"},{"id":1,"name":"Swanson Macdonald"},{"id":2,"name":"Gill Maynard"}]
     * greeting : Hello, Thornton! You have 10 unread messages.
     * favoriteFruit : banana
     */

    private String _id;
    private int index;
    private String guid;
    private boolean ifActive;
    private String balance;
    private String picture;
    private int age;
    private String eyeColor;
    private NameBean name;
    private String company;
    private String email;
    private String phone;
    private String address;
    private String about;
    private String registered;
    private String latitude;
    private String longitude;
    private String greeting;
    private String favoriteFruit;
    private List<String> tags;
    private List<Integer> range;
    private List<String> friends;

    public static class NameBean {
        /**
         * first : Thornton
         * last : Hendrix
         */

        private String first;
        private String last;

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }
}
