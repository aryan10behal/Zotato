import java.util.ArrayList;
import java.util.Scanner;


//Aryan Behal    2019026 CSE
// for the orders already placed by customer
class orders_placed{

    //attributes
    ArrayList<Cart> order_list;
    int total_no_of_orders;

    //constructor
    orders_placed()
    {
        order_list= new ArrayList<Cart>();
        total_no_of_orders=0;
    }
    //methods
    void insert_placed_orders(Cart c)           //insert the order just concluded to the list
    {
        if(order_list.size()>=10)               //tp maintain order list of size 10
        {
            order_list.remove(0);
        }
        order_list.add(total_no_of_orders,c);
        total_no_of_orders++;
    }
    void display_orders_already_placed()        //display list of orders placed
    {
        for(int i=0;i<order_list.size();i++)
        {
            System.out.println("\n\nOrder "+Integer.toString(i+1)+": ");
            order_list.get(i).display_food_in_cart();
        }
    }

    //getter
    int get_no_of_orders_placed()
    { return this.total_no_of_orders; }
}


//for Customer cart in app
class Cart{

    //attributes
    ArrayList<food_item> food_in_cart;
    ArrayList<Integer> quantity_ordered;
    ArrayList<Float> final_individual_food_amount;
    float final_amount;
    int total_quantity;
    float customer_delivery_fees;
    int restro_number;

    //constructor
    Cart(){
        food_in_cart=new ArrayList<food_item>();
        quantity_ordered=new ArrayList<Integer>();
        final_individual_food_amount=new ArrayList<Float>();
        customer_delivery_fees=0;
        final_amount=0;
        total_quantity=0;
        restro_number=-1;
    }

    //methods
    void new_food_to_cart(food_item f, int quantity, float discount_amount,int rest_num)
    {
        food_in_cart.add(f);
        quantity_ordered.add(quantity);
        final_individual_food_amount.add(discount_amount);
        restro_number=rest_num;
    }

    //to copy a cart to other cart (needed before we remove items from cart)
    void copycart(Cart c)
    {
        this.restro_number=c.restro_number;
        this.customer_delivery_fees=c.customer_delivery_fees;
        this.total_quantity=c.total_quantity;
        this.final_amount=c.final_amount;
        for(int i=0;i<c.food_in_cart.size();i++)
        {
            food_item f=new food_item();
            c.food_in_cart.get(i).food_copy(f);
            this.food_in_cart.add(f);
        }
        for(int i=0;i<c.quantity_ordered.size();i++)
        {
            this.quantity_ordered.add(c.quantity_ordered.get(i));
            this.final_individual_food_amount.add(c.final_individual_food_amount.get(i));
        }
    }

    //to remove items from cart
    void clear()
    {   int k=food_in_cart.size();
        int i=0;
        while(i<k)
        {   food_in_cart.remove(0);
            quantity_ordered.remove(0);
            final_individual_food_amount.remove(0);
            i++; }
        customer_delivery_fees=0;
        final_amount=0;
        total_quantity=0;
        restro_number=-1; }

    void display_food_in_cart()             //to_display_orders_already_bought
    {
        for(int i=0;i<food_in_cart.size();i++)
        {
            System.out.print("-> ");
            System.out.print("Bought: ");
            System.out.print(food_in_cart.get(i).get_food_name());
            System.out.print(", Quantity: ");
            System.out.print(quantity_ordered.get(i));
            System.out.print(", (without customer and overall discount)For Rs: ");
            System.out.println(final_individual_food_amount.get(i)*quantity_ordered.get(i));
        }

        System.out.print(" -->> From Restaurant: ");
        System.out.println(food_in_cart.get(0).get_restaurant_name());
       this.generated_bill_display();
    }
    void display()       //to display orders in cart
    {
        for(int i=0;i< food_in_cart.size();i++)
        {
            food_in_cart.get(i).print_food_item(quantity_ordered.get(i));
        }
        this.generated_bill_display();
    }
    void calculate_final_amount()                                 //calculate amounts without overall discounts..
    { for(int i=0;i<final_individual_food_amount.size();i++)
        {
            final_amount+=(final_individual_food_amount.get(i)*quantity_ordered.get(i));
            total_quantity+=quantity_ordered.get(i); } }

    void generated_bill_display()
    {
        System.out.print("-->> Delivery charges: INR ");
        System.out.println(customer_delivery_fees);
        System.out.print("-->> Total order Value: INR ");
        System.out.println(final_amount+customer_delivery_fees);
    }
    float checkout_cart()
    {
        float money_paid=final_amount+customer_delivery_fees;
        System.out.print(total_quantity);
        System.out.print(" Items bought successfully for INR ");
        System.out.print(final_amount+customer_delivery_fees);
        return money_paid;
    }
    //getter
    float get_final_amount()
    { return this.final_amount; }
    float get_customer_delivery_fees()
    { return this.customer_delivery_fees; }
    int get_restro_number()
    { return restro_number; }
    int get_size()
    { return this.food_in_cart.size(); }

    //setter
    void set_final_amount(float x)
    {
        this.final_amount=x;
    }
    void set_customer_delivery_fess(float x)
    {
        this.customer_delivery_fees=x;
    }
}
class food_item{
    private int id;
    private String restaurant_name;
    private String food_name;
    private float item_price;
    private int quantity_with_restaurant;
    private String item_category;
    private float offer;
    Scanner in= new Scanner(System.in);

    //constructor
    public food_item() {
    }
    public void set_up_food_item(int id,String name)
    {   System.out.println("Enter the food item details..");
        this.id=id;
        this.restaurant_name=name;
        System.out.print("Food name: ");
        this.food_name=in.next();
        System.out.print("Item price: ");
        this.item_price=in.nextFloat();
        System.out.print("Item quantity: ");
        this.quantity_with_restaurant=in.nextInt();
        System.out.print("Item Category: ");
        this.item_category=in.next();
        System.out.print("Offer: ");
        this.offer=in.nextFloat(); }
    void food_copy(food_item f)
    {   f.id=this.id;
        f.restaurant_name=this.restaurant_name;
        f.food_name=this.food_name;
        f.item_price=this.item_price;
        f.quantity_with_restaurant=this.quantity_with_restaurant;
        f.item_category=this.item_category;
        f.offer=this.offer;
    }
    public void print_food_item(int quantity)    //print food item
    {
        System.out.print("-> ");
        System.out.print(this.id);
        System.out.print(" "+this.restaurant_name+" "+this.food_name+" ");
        System.out.print(this.item_price);
        System.out.print(" ");
        if(quantity==0)
            System.out.print(this.quantity_with_restaurant);
        else
            System.out.print(quantity);
        System.out.print(" ");
        System.out.print(this.offer);
        System.out.println("% off "+this.item_category);
    }
    //setter
    public void set_food_name(String a)
    {
        this.food_name=a;
    }
    public void set_item_price(float a)
    {
        this.item_price=a;
    }
    public void set_Quantity_with_restaurant(int x)
    {
        this.quantity_with_restaurant=x;
    }
    public void set_category(String y)
    {
        this.item_category=y;
    }
    public void set_offer(float a)
    {
        this.offer=a;
    }
    public void set_quantity_left_with_restaurant(int x){ this.quantity_with_restaurant-=x; }

    //getter
    float get_item_price()
    {
        return this.item_price;
    }
    String get_restaurant_name()
    { return this.restaurant_name; }
    int get_quantity_with_restaurant()
    { return this.quantity_with_restaurant; }
    float get_offer()
    {
        return this.offer;
    }
    public String get_food_name()
    { return this.food_name; }

}
class User
{
    protected int id;
    protected String name;
    protected String address;
    protected float reward_points;
    protected Scanner in= new Scanner(System.in);

    //constructors
    User(){ }
    User(int id,String name,String address, float points) {
        this.id=id;
        this.name = name;
        this.address = address;
        reward_points = points;
    }
    protected void display_user()
    {
        System.out.print(this.id);
        System.out.print(". ");
        System.out.print(this.name);
    }

    //getter
    protected String get_name()
    {
        return this.name;
    }

    //setter
    protected void set_points(float points)
    { reward_points=points; }
    protected float get_points()
    { return this.reward_points; }

}

class Restaurant extends User{
    protected ArrayList<food_item> food_list;
    private int current_ID;
    private float overall_discount;
    int no_of_orders;

    //constructors
    public Restaurant()
    {
        super();
        food_list= new ArrayList<food_item>();
        no_of_orders=0;
        current_ID=0;
        overall_discount=0;
    }
    public Restaurant(int id,String name, String address)
    {
        super(id,name,address,(float)0);
        food_list= new ArrayList<food_item>();
        no_of_orders=0;
        current_ID=0;
        overall_discount=0;
    }

    //methods
    public int display_food_list()
    {
        if(this.food_list.size()==0)
        {
            System.out.println("No food served...");
            return -1;
        }
        else {
            for (int i = 0; i < food_list.size(); i++) {
                food_list.get(i).print_food_item(0); }
        }
        return 0;
    }
    public void display_restaurant()
        { super.display_user();
        System.out.println(); }
    public void edit_food_list()
    {
        System.out.println("\nChoose item by code: ");
        int item_number=in.nextInt();
        System.out.println("Choose an attribute to edit:\n  1)Name\n  2)Price\n  3)Quantity\n  4)Category\n  5)Offer ");
        int choice=in.nextInt();
        switch(choice)
        {
            case 1: System.out.print("Enter the name: ");
                    String a=in.next();
                    food_list.get(item_number-1).set_food_name(a);
                    break;
            case 2: System.out.print("Enter the price: ");
                    float b=in.nextFloat();
                    food_list.get(item_number-1).set_item_price(b);
                    break;
            case 3: System.out.print("Enter the Quantity: ");
                    int x=in.nextInt();
                    food_list.get(item_number-1).set_Quantity_with_restaurant(x);
                    break;
            case 4:System.out.print("Enter the Category: ");
                    String y=in.next();
                    food_list.get(item_number-1).set_category(y);
                    break;
            case 5: System.out.print("Enter Offer: ");
                    float z=in.nextFloat();
                    food_list.get(item_number-1).set_offer(z);
                    break;
            default:System.out.println("\n##Wrong input##");
        }
        System.out.print("After changes, food: ");
        food_list.get(item_number-1).print_food_item(0);
    }
    int increase_id()
    { this.current_ID++;
    return this.current_ID;
    }

    //setter
    void set_no_of_orders(int x)
    { this.no_of_orders=x; }
    void set_items_left(int food_number,int quantity)
    { this.food_list.get(food_number-1).set_quantity_left_with_restaurant(quantity); }

    //getter
    float get_food_discounted_price(int food_number)
    {
        float x= this.food_list.get(food_number-1).get_item_price();
        float y=this.food_list.get(food_number-1).get_offer();
        return x*(1-(y/100));
    }
    int food_items_left(int food_number)
    {
        return this.food_list.get(food_number-1).get_quantity_with_restaurant();
    }

    void add_to_food_list(food_item f)
    { this.food_list.add(f); }
    void setOverall_discount(float a)
    { this.overall_discount=0; }
    float get_overall_restaurant_discount(float food_discounted_price)
    {
        float restaurant_discount_price= food_discounted_price;
        return restaurant_discount_price;
    }
    food_item get_specific_food(int food_number)
    {
        return this.food_list.get(food_number-1);
    }
    float reward_setter(float bill)
    {
        float reward;
        int ratio = (int) (bill/100);
        reward=5*ratio;
        return reward;
    }


    //interface possible
    void display_details()
    { System.out.print(this.name+", "+this.address+", "+this.no_of_orders); }
}

class Authentic extends Restaurant{
    float overall_discount;

    //constructor
    Authentic()
    { super();
    overall_discount=0; }
    Authentic(int id,String name, String address)
    {
        super(id,name,address);
        overall_discount=0;
    }

    @Override
    public void display_restaurant()
    {
        super.display_user();
        System.out.println(" (Authentic)");
    }
    @Override
    void setOverall_discount(float a)
    {
        this.overall_discount=a;
    }

    @Override
    float get_overall_restaurant_discount(float food_discounted_price)
    {
        float y=this.overall_discount/100;
        float x= (float) (1.0-y);
        float restaurant_discount_price= food_discounted_price*(x);
        if(restaurant_discount_price>100)
            restaurant_discount_price-=50;
        return restaurant_discount_price;
    }

    @Override
    float reward_setter(float bill)
    {
        float reward;
        int ratio = (int) (bill/200);
        reward=25*ratio;
        return reward;
    }

    @Override
    void display_details()
    {
        System.out.print(this.name+"(Authentic), "+this.address+", "+this.no_of_orders);
    }
}

class Fast_Food extends Restaurant{
    float overall_discount;
    Fast_Food()
    {
        super();
        overall_discount=0;
    }
    Fast_Food(int id,String name, String address)
    {
        super(id,name,address);
        overall_discount=0;
    }
    @Override
    public void display_restaurant()
    {
        super.display_user();
        System.out.println(" (Fast Food)");
    }
    @Override
    void setOverall_discount(float a)
    {
        this.overall_discount=a;
    }

    @Override
    float get_overall_restaurant_discount(float food_discounted_price)
    {
        float y=this.overall_discount/100;
        float x= (float) (1.0-y);
         float restaurant_discount_price= food_discounted_price*(x);
         return restaurant_discount_price;
    }

    @Override
    float reward_setter(float bill)
    {
        float reward;
        int ratio = (int) (bill/150);
        reward=10*ratio;
        return reward;
    }
    @Override
    void display_details()
    { System.out.print(this.name+"(Fast Food), "+this.address+", "+this.no_of_orders); }
}

class Customer extends User{
    protected float wallet;
    protected orders_placed orders;
    protected Cart cart;
    private float delivery_charges;

    //constructors
    public Customer()
    {
        super();
        wallet=1000;
        cart=new Cart();
        orders=new orders_placed();
        delivery_charges=40;
    }
    public Customer(int id,String name, String address)
    {
        super(id,name,address, (float) 0.0);
        wallet=1000;
        cart=new Cart();
        orders=new orders_placed();
        delivery_charges=40;
    }

    public void display_customers()
    {
        super.display_user();
        System.out.println();
    }
    void display_placed_orders()
    {
        if(orders!=null)
            orders.display_orders_already_placed();
        else
            System.out.println("No order placed yet..");
    }
    public void add_to_customer_cart(food_item f, int quantity,float amount,int restro_number)
    { cart.new_food_to_cart(f,quantity,amount,restro_number);
    }
    void calculate_amount()
    { this.cart.calculate_final_amount(); }
    int display_cart()
    {
        if(this.cart.get_size()>0)
        {
            this.cart.display();
            return 0;
        }
        else
        {
            System.out.println("Please select some item..");
            return -1;
        }
    }
    void customer_special_off()
    {
        delivery_charges=40;
        this.cart.set_customer_delivery_fess(delivery_charges);
    }

    void display_details()
    { System.out.print(this.name+", "+this.address+", "+this.wallet); }
    int cart_get_restro_number()
    { return this.cart.get_restro_number(); }

    void show_bill()
    {
        this.display_cart();
    }
    float buy()
    {
        float money_paid=-1;
        System.out.println("1. Proceed to check out");
        int choice=in.nextInt();
        if(choice==1) {
            if (this.wallet + this.reward_points > cart.get_final_amount()+cart.get_customer_delivery_fees()) {
                money_paid = this.cart.checkout_cart();
                Cart placed_cart=new Cart();
                this.copying_cart(placed_cart,this.cart);
                orders.insert_placed_orders(placed_cart);
                this.cart.clear();
                float points=this.get_points();
                if (points > 0) {
                    points -= money_paid;
                    if (points < 0) {
                        wallet += points;
                        points = 0;
                    }
                    this.set_points(points);
                } else {
                    wallet -= money_paid;
                }
            } else {
                System.out.printf("Not enough money..Add more money to buy the cart..");
            }
        }
        return money_paid;
    }

    void copying_cart(Cart b,Cart c)
    {
        b.copycart(c);
    }

    //setter
    void set_total_food_amount(float x)
    { this.cart.set_final_amount(x); }

    //getter
    float get_total_food_amt()
    { return this.cart.get_final_amount(); }
    float get_delivery_charges()
    { return this.cart.get_customer_delivery_fees(); }
    int get_order_size()
    { return this.orders.get_no_of_orders_placed(); }
    int get_cart_size()
    { return this.cart.get_size(); }
}

class Elite extends Customer{
    private float delivery_charges;

    //constructor
    public Elite()
    {
        super();
        delivery_charges=0;
    }
    public Elite(int id,String name, String address)
    {
        super(id,name,address);
        delivery_charges=0;
    }

    @Override
    void customer_special_off()
    {
        delivery_charges=0;
        this.cart.set_customer_delivery_fess(delivery_charges);
        float amt=this.cart.get_final_amount();
        if(amt>200)
        {
            this.cart.set_final_amount(amt-50);
        }
    }

    @Override
    void display_details()
    {
        System.out.print(this.name+"(Elite), "+this.address+", "+this.wallet);
    }
    @Override
    public void display_customers()
    {
        super.display_user();
        System.out.println(" (Elite)");
    }
}

class Special extends Customer{
    private float delivery_charges;
    public Special()
    {
        super();
        delivery_charges=0;
    }
    public Special(int id,String name, String address)
    {
        super(id,name,address);
        delivery_charges=0;
    }
    @Override
    void customer_special_off()
    {
        delivery_charges=20;
        this.cart.set_customer_delivery_fess(delivery_charges);
        float amt=this.cart.get_final_amount();
        if(amt>200)
        {
            this.cart.set_final_amount(amt-25);
        }
    }
    @Override
    void display_details()
    {
        System.out.print(this.name+"(Special), "+this.address+", "+this.wallet);
    }
    @Override
    public void display_customers()
    {
        super.display_user();
        System.out.println(" (Special)");
    }
}

class Zotato
{
    ArrayList<Customer> customers;
    ArrayList<Restaurant> restaurants;
    float company_balance;
    float total_delivery_charges;
    Scanner in=new Scanner(System.in);

    Zotato()
    {
        customers=new ArrayList<Customer>();
        restaurants=new ArrayList<Restaurant>();
        company_balance=0;
        total_delivery_charges=0;
    }
    void set_up_customers()
    {
        customers.add(new Elite(1,"Ram","ring road"));
        customers.add(new Elite(2,"Sam","okhla"));
        customers.add(new Special(3,"Tim","Sadar"));
        customers.add(new Customer(4,"Kim","gurgaon"));
        customers.add(new Customer(5,"Jim","Dwarka"));
    }
    void set_up_restaurants()
    {
        restaurants.add(new Authentic(1,"Shah","CP"));
        restaurants.add(new Restaurant(2,"Ravi's","Janakpuri"));
        restaurants.add(new Authentic(3,"The Chinese","Tilak Nagar"));
        restaurants.add(new Fast_Food(4,"Wang's","NSP"));
        restaurants.add(new Restaurant(5,"Paradise","Gandhi Nagar"));
    }
    void Begin_App()
    {
        while(true)
        {System.out.println("\nWelcome to Zotato..");
            System.out.println("\n  1. Enter as Restaurant owner\n  2. Enter as Customer\n  3.Check User Details\n  4.Company Account Details\n  5.Exit");
            int value=in.nextInt();
            switch(value)
            {
                case 1:
                    this.display_restaurant_list();
                    int restro_number=in.nextInt();
                    this.queries_to_restaurant(restro_number);
                    break;
                case 2:
                    this.display_customer_list();
                    int customer_number=in.nextInt();
                    System.out.println("Welcome "+customers.get(customer_number-1).get_name());
                    this.customer_menu(customer_number);
                    break;
                case 3:
                    System.out.println("\n 1. Customer details\n 2. Restaurant details");
                    int choice=in.nextInt();
                    if(choice==1)
                    {
                        this.display_customer_list();
                        int custm_number=in.nextInt();
                        if(this.customers.get(custm_number-1) instanceof Elite)
                            ((Elite)this.customers.get(custm_number-1)).display_details();
                        else if(this.customers.get(custm_number-1) instanceof Special)
                            ((Special)this.customers.get(custm_number-1)).display_details();
                        else
                            this.customers.get(custm_number-1).display_details();
                    }
                    else if(choice==2)
                    {
                        this.display_restaurant_list();
                        int rest_number=in.nextInt();
                        if(this.restaurants.get(rest_number-1) instanceof Fast_Food)
                            ((Fast_Food)this.restaurants.get(rest_number-1)).display_details();
                        else if(this.restaurants.get(rest_number-1) instanceof Authentic)
                            ((Authentic)this.restaurants.get(rest_number-1)).display_details();
                        else
                            this.restaurants.get(rest_number-1).display_details();
                    }
                    else
                    {
                        System.out.println("Enter correct value..");
                    }
                    break;
                case 4:System.out.print("Total Company Balance: INR ");
                        System.out.println(this.company_balance);
                        System.out.print("Total delivery charges: INR ");
                        System.out.println(this.total_delivery_charges);
                        break;
                case 5:return;
                default:System.out.println("Enter correct Input");


            }
        }
    }

    public void queries_to_restaurant(int restro_number) {

        while (true) {
            System.out.println("\n->Welcome to " + restaurants.get(restro_number-1).get_name());
            System.out.println("\t1) Add item\n\t2)Edit item\n\t3)Print rewards\n\t4)Discount on bill value\n\t5)Exit");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    int id=restaurants.get(restro_number-1).increase_id();
                    food_item f = new food_item();
                    f.set_up_food_item(id,this.restaurants.get(restro_number-1).get_name());
                    System.out.print("New food added: ");
                    f.print_food_item(0);
                    restaurants.get(restro_number-1).add_to_food_list(f);
                    break;
                case 2:
                    int check=this.restaurants.get(restro_number-1).display_food_list();
                    if(check==0)
                        this.restaurants.get(restro_number-1).edit_food_list();
                    break;
                case 3:
                    System.out.print("Reward_points: ");
                    System.out.println(this.restaurants.get(restro_number-1).reward_points);
                    break;
                case 4:
                    if (this.restaurants.get(restro_number-1) instanceof Fast_Food || this.restaurants.get(restro_number-1) instanceof Authentic) {
                        System.out.print("Offer on bill  value: ");
                        float a=in.nextFloat();
                        if(restaurants.get(restro_number-1) instanceof Authentic)
                        {
                            ((Authentic) restaurants.get(restro_number-1)).setOverall_discount(a);
                        }
                        else if(restaurants.get(restro_number-1) instanceof Fast_Food)
                        {
                            ((Fast_Food) restaurants.get(restro_number-1)).setOverall_discount(a);
                        }

                    } else {
                        System.out.println("No discount offered..");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter correct value..");
            }
        }
    }

    void customer_menu(int customer_number)
    {
        while(true) {
            System.out.println("\nCustomer Menu..");
            System.out.println("1. Select Restaurant\n2.Checkout cart\n3.Reward won\n4.print the recent order\n5.exit");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Choose restaurant..");
                    this.display_restaurant_list();
                    int restro_number = in.nextInt();
                    System.out.println("Choose item by code: ");
                    int check=this.restaurants.get(restro_number - 1).display_food_list();
                    if(check==0) {
                        int food_number = in.nextInt();
                        System.out.println("Enter quantity: ");
                        int quantity = in.nextInt();
                        if(quantity<restaurants.get(restro_number-1).food_items_left(food_number)) {
                            float discounted_price_on_food = this.calculate_food_discount_price(customer_number, restro_number, food_number, quantity);
                            food_item f = restaurants.get(restro_number - 1).get_specific_food(food_number);
                            restaurants.get(restro_number-1).set_items_left(food_number,quantity);
                            this.customers.get(customer_number - 1).add_to_customer_cart(f, quantity, discounted_price_on_food, restro_number);
                            System.out.println("Item added to cart..");
                        }
                        else
                        {
                            System.out.println("Restaurant doesn't have enough food item...");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Items in cart..");
                    int size=this.customers.get(customer_number - 1).get_cart_size();
                    if(size>0) {
                        if (customers.get(customer_number - 1) instanceof Elite) {
                            ((Elite) customers.get(customer_number - 1)).customer_special_off();
                        } else if (customers.get(customer_number - 1) instanceof Special) {
                            ((Special) customers.get(customer_number - 1)).customer_special_off();
                        } else {
                            customers.get(customer_number - 1).customer_special_off();
                        }
                        this.customers.get(customer_number-1).calculate_amount();
                        int rest_number = customers.get(customer_number-1).cart_get_restro_number();
                        float discounted_price_from_restaurant=this.calculate_charges(this.customers.get(customer_number-1).get_total_food_amt(),rest_number);
                        customers.get(customer_number-1).set_total_food_amount(discounted_price_from_restaurant);
                        customers.get(customer_number - 1).show_bill();
                        float total_food_amt = this.customers.get(customer_number - 1).get_total_food_amt();
                        this.company_balance += (0.01) * total_food_amt;
                        float total_delivery = this.customers.get(customer_number - 1).get_delivery_charges();
                        this.total_delivery_charges += total_delivery;
                        float bill = customers.get(customer_number - 1).buy();
                        int no_of_orders_placed = customers.get(customer_number - 1).get_order_size();
                        this.restaurants.get(rest_number - 1).set_no_of_orders(no_of_orders_placed);
                        this.give_reward(bill, customer_number, rest_number);
                    }
                    break;
                case 3:
                    System.out.print("Reward won: ");
                    System.out.print(this.customers.get(customer_number-1).get_points());
                    break;
                case 4:
                    this.customers.get(customer_number - 1).display_placed_orders();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter correct value"); }
        }
    }

    void give_reward(float bill, int customer_number, int restro_number)
    {
        float reward_given;
        if(this.restaurants.get(restro_number-1) instanceof Fast_Food)
            reward_given=((Fast_Food)restaurants.get(restro_number-1)).reward_setter(bill);
        else if(this.restaurants.get(restro_number-1) instanceof Authentic)
            reward_given=((Authentic)restaurants.get(restro_number-1)).reward_setter(bill);
        else
            reward_given=restaurants.get(restro_number-1).reward_setter(bill);
        this.customers.get(customer_number-1).set_points(reward_given);
        this.restaurants.get(customer_number-1).set_points(reward_given);
    }
    float calculate_food_discount_price(int customer_number,int restro_number, int food_number,int quantity)
    {
        float food_discounted_price= this.restaurants.get(restro_number-1).get_food_discounted_price(food_number);
        return food_discounted_price;
    }
    float calculate_charges(float food_discounted_price,int restro_number)
    {
        float restaurant_discount_price;
            if(restaurants.get(restro_number-1) instanceof Authentic)
            {
                restaurant_discount_price=((Authentic)restaurants.get(restro_number-1)).get_overall_restaurant_discount(food_discounted_price);
            }
            else if(restaurants.get(restro_number-1) instanceof Fast_Food)
            {
                restaurant_discount_price=((Fast_Food)restaurants.get(restro_number-1)).get_overall_restaurant_discount(food_discounted_price);
            }
            else
            {
                restaurant_discount_price=restaurants.get(restro_number-1).get_overall_restaurant_discount(food_discounted_price);
            }

        return restaurant_discount_price;
    }
    void display_restaurant_list()
    {
        for(int i=0;i<this.restaurants.size();i++)
        {
            if(restaurants.get(i) instanceof Authentic)
                {
                    Authentic authentic = (Authentic) restaurants.get(i);
                    authentic.display_restaurant();
                }
            else if(restaurants.get(i) instanceof Fast_Food)
                  {
                      Fast_Food fast_food = (Fast_Food) restaurants.get(i);
                      fast_food.display_restaurant();
                  }
            else
                {
                    restaurants.get(i).display_restaurant();
                }
        }
    }
    void display_customer_list()
    {
        for(int i=0;i<this.customers.size();i++)
        {
            if(customers.get(i) instanceof Elite)
            {
                Elite h = (Elite) customers.get(i);
                h.display_customers();
            }
            else if(customers.get(i) instanceof Special)
            {
                Special h = (Special) customers.get(i);
                h.display_customers();
            }
            else{
                customers.get(i).display_customers();
            }
        }
    }
}
public class assignment_2 {
    public static void main(String[] Args)
    {
        Zotato app=new Zotato();
        app.set_up_customers();
        app.set_up_restaurants();
        app.Begin_App();
    }
}
