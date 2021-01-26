package jpabook.japshop;

import jpabook.japshop.domain.Address;
import jpabook.japshop.domain.Member;
import jpabook.japshop.domain.Order;
import jpabook.japshop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaShop");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("한준희");
            //member.setCity("용인");
            member.setCreateBy("tester");
            member.setCreateLocalDateTime(LocalDateTime.now());

            Address address1 = new Address();
            address1.setCity("용인");
            address1.setStreet("역북");
            address1.setZipcode("1000");
            member.setAddress(address1);


            em.persist(member);

            Order order = new Order();
            order.setMember(member);

            em.persist(order);

            Order findOrder = em.find(Order.class, order.getId());
            System.out.println("findOrder.getMember().getName() = " + findOrder.getMember().getName());
            System.out.println("findOrder.getMember().getCity() = " + findOrder.getMember().getAddress());

            member.getAddress().setCity("서울");
            System.out.println("findOrder.getMember().getCity() = " + findOrder.getMember().getAddress());


            Address address2 = new Address();
            address2.setCity("용인");
            address2.setStreet("역북");
            address2.setZipcode("2000");
            member.setAddress(address2);

            Member member2 = new Member();
            member2.setName("한재희");
            //member2.setCity("용인");
            member2.setAddress(address2);

            em.persist(member2);

            Order order2 = new Order();
            order2.setMember(member2);

            em.persist(order2);

            Order findOrder2 = em.getReference(Order.class, order2.getId());
            System.out.println("findOrder.getMember2().getName() = " + findOrder2.getMember().getName());
            System.out.println("findOrder.getMember2().getCity() = " + findOrder2.getMember().getAddress());


            OrderItem orderItem1 = new OrderItem();
            orderItem1.setOrderPrice(100);
            orderItem1.setCount(100);

            OrderItem orderItem2 = new OrderItem();
            orderItem2.setOrderPrice(300);
            orderItem2.setCount(100);

            OrderItem orderItem3 = new OrderItem();
            orderItem3.setOrderPrice(400);
            orderItem3.setCount(100);

            //양방향 설정을 해줘야함 ************
            //orderItem3.chageOrder(findOrder2);
            //findOrder2.getOrderItems().add(orderItem1);
            findOrder2.setAddOrderItem(orderItem3);


            em.persist(orderItem1);
            em.persist(orderItem2);
            em.persist(orderItem3);

          /* em.flush();
            em.clear();*/


            /*OrderItem orderItem = em.find(OrderItem.class, orderItem3.getId());

            System.out.println("==================");
            for (OrderItem item : orderItems) {
                System.out.println("item.getOrderPrice() = " + item.getOrderPrice());
            }
            System.out.println("==================");
*/

           /* Order lastFindOrder = em.find(Order.class, 5L);

            List<OrderItem> orderItemList = new ArrayList<>();
            orderItemList.add(orderItem1);
            orderItemList.add(orderItem2);
            orderItemList.add(orderItem3);

            lastFindOrder.setOrderItem(orderItemList);

            for(OrderItem orderItem : lastFindOrder.getOrderItem()) {
                System.out.println("orderItem = " + orderItem.getId());
            }*/


            tx.commit();

        } catch (Exception e) {
            System.out.println(e.getStackTrace()[1]);
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();


    }
}
