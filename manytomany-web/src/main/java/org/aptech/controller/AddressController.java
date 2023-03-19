package org.aptech.controller;

import org.aptech.entity.Address;
import org.aptech.entity.Person;
import org.aptech.services.EntityService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/address")
public class AddressController extends HttpServlet {
    @EJB
    EntityService<Person> personEntityService;

    @EJB
    EntityService<Address> addressEntityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action")==null?"ADD":request.getParameter("action");
        if(action.equalsIgnoreCase("ADD")){
            request.getServletContext().getRequestDispatcher("/create.jsp").forward(request,response);
        } else if (action.equalsIgnoreCase("SEARCH") ) {
            request.getServletContext().getRequestDispatcher("/search.jsp").forward(request,response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action")==null?"ADD":req.getParameter("action");
        if(action.equalsIgnoreCase("ADD")){
            addPerson(req,resp);
        } else if (action.equalsIgnoreCase("SEARCH") ){
            personEntityService.setType(Person.class);
            String perId = req.getParameter("id");
            Person person = personEntityService.getEntityById(Long.valueOf(perId));
            req.setAttribute("person",person);
            req.getServletContext().getRequestDispatcher("/result.jsp").forward(req,resp);
        }

    }

    private void addPerson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        personEntityService.setType(Person.class);
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String street = req.getParameter("street");
        String number = req.getParameter("number");
        if(action.equalsIgnoreCase("ADD")){
            Person person = new Person();
            person.setName(name);
            Address address = new Address();
            address.setStreet(street);
            address.setNumber(number);
            person.getAddresses().add(address);
            personEntityService.addEntity(person);
            resp.getWriter().write("Create Person success");


        }
    }


}
