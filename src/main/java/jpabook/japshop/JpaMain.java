package jpabook.japshop;

import jpabook.japshop.domain.Member;
import jpabook.japshop.domain.Order;
import jpabook.japshop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaShop");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("한준희");
            member.setCity("용인");

            em.persist(member);

            Order order = new Order();
            order.setMember(member);

            em.persist(order);

            Order findOrder = em.find(Order.class, order.getId());
            System.out.println("findOrder.getMember().getName() = " + findOrder.getMember().getName());
            System.out.println("findOrder.getMember().getCity() = " + findOrder.getMember().getCity());

            member.setCity("서울");
            System.out.println("findOrder.getMember().getCity() = " + findOrder.getMember().getCity());

            Member member2 = new Member();
            member2.setName("한재희");
            member2.setCity("용인");

            em.persist(member2);

            Order order2 = new Order();
            order2.setMember(member2);

            em.persist(order2);

            Order findOrder2 = em.find(Order.class, order2.getId());
            System.out.println("findOrder.getMember2().getName() = " + findOrder2.getMember().getName());
            System.out.println("findOrder.getMember2().getCity() = " + findOrder2.getMember().getCity());


            OrderItem orderItem1 = new OrderItem();
            orderItem1.setOrderPrice(100);
            orderItem1.setCount(100);

            OrderItem orderItem2 = new OrderItem();
            orderItem2.setOrderPrice(300);
            orderItem2.setCount(100);

            OrderItem orderItem3 = new OrderItem();
            orderItem3.setOrderPrice(400);
            orderItem3.setCount(100);

            orderItem3.setOrder(findOrder2);

            em.persist(orderItem1);
            em.persist(orderItem2);
            em.persist(orderItem3);

            em.flush();
            em.clear();


            OrderItem orderItem = em.find(OrderItem.class, orderItem3.getId());

            List<OrderItem> orderItems = orderItem.getOrder().getOrderItems();
            for (OrderItem item : orderItems) {
                System.out.println("item.getOrderPrice() = " + item.getOrderPrice());
            }



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
