package com.github.devfrogora;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.catalina.connector.Response;
import org.json.JSONObject;

@WebServlet("/create_order")
public class FirstServlet extends HttpServlet {

    String key = "rzp_test_hyRWe2OzpfSajo";
    String secret = "EhNJX9giFfSxLMPchEVkIzUP";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Welcome to servlet</h2>");
        try {
            Integer amount = Integer.parseInt(req.getParameter("amount"));
            if (amount == null) {
                out.println("amount is null");
            }
            out.println(amount);

            RazorpayClient razorpayClient = new RazorpayClient(key, secret);
            JSONObject options = new JSONObject();
            options.put("amount", amount*100);
            options.put("currency", "INR");
            options.put("receipt", "txn_123456");
            Order order = razorpayClient.Orders.create(options);
            System.out.println(amount);
            System.out.println(order);
            out.println(order);

        } catch (Exception e) {
            out.println("enter valid number : <br>Exception name" + e.getClass() + "<br>Message: " + e.getMessage());
            System.err.println(e.getMessage());

        }
    }

}
