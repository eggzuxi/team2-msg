package org.example.team2msg.msg.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.team2msg.msg.message.dao.MsgDAO;
import org.example.team2msg.msg.message.MsgVO;
import org.example.team2msg.common.PageInfo;

import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/studentlist/sent")
public class StudentSentListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("StudentSentList doGet");

        HttpSession session = req.getSession();
        StudentVO student = (StudentVO) session.getAttribute("student");

        if (student == null) {
            resp.sendRedirect(req.getContextPath() + "/login?error=Not logged in");
            return;
        }

        String sid = student.getSid();
        int page = Integer.parseInt(req.getParameter("page") == null ? "1" : req.getParameter("page"));
        int size = 10; // 페이지당 표시할 메시지 수

        try {
            int total = MsgDAO.INSTANCE.getTotalSentCount(sid); // 총 보낸 메시지 수를 가져오는 메서드
            req.setAttribute("page", page);
            req.setAttribute("size", size);
            req.setAttribute("total", total);
            log.info("total: {} size: {} page: {}",total,size,page);

            PageInfo pageInfo = new PageInfo(page, size, total);


            List<MsgVO> messages = MsgDAO.INSTANCE.getSentMessages(sid, page, size);
            req.setAttribute("pageInfo", pageInfo);
            req.setAttribute("messageType", "sent");
            req.setAttribute("messages", messages);
            req.setAttribute("studentName", student.getSid());
            req.getRequestDispatcher("/WEB-INF/student/studentsentlist.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error retrieving messages", e);
            resp.sendRedirect(req.getContextPath() + "/studentlist?error=server_error");
        }
    }
}
