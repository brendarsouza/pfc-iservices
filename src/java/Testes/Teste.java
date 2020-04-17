/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Controller.ProfissionalControle;
import DAO.ClienteDAO;
import DAO.LogsDAO;
import DAO.PedidoDAO;

import DAO.ProfissionalDAO;
import DAO.UsuarioDAO;
import DAO.VisitaDAO;
import Model.Cliente;
import Model.Logs;
import Model.Pedido;
import Model.PerfilAcesso;
import Model.Profissional;
import Model.Status_Pedido;
import Model.Status_Visita;
import Model.Usuario;
import Model.Visita;
import Util.Email;
import javax.persistence.Query;
import Util.JPAUtil;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brenda
 */
public class Teste {

    public static void main(String[] args) throws Exception {
        
         int idProfissional = 1;

        VisitaDAO visitaDAO = new VisitaDAO();
        List<Visita> listaVisitas = visitaDAO.buscarVisitasProfissional(idProfissional);

        
        
        System.out.println("Lista De visitas:" +listaVisitas);
//        Date dataInicial, dataFinal;
//        String strDataInicial = "14/01/2017 23:45";
//        String strDataFinal = "14/01/2017 00:00";
//
//        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//
//        dataFinal = sdf1.parse(strDataFinal);
//        dataInicial = sdf2.parse(strDataInicial);
//
//        System.out.println(dataInicial.compareTo(dataFinal));
//        System.out.println(dataInicial);
//        System.out.println(dataInicial.after(dataFinal));
//        int idVisita = 1;
//        int idPedido = 1;
//
//        VisitaDAO visitaDAO = new VisitaDAO();
//        Visita visita = visitaDAO.getById(idVisita);
//
//        String novaDataVisita = "";
//        String novoHorarioVisita = "";
//
//        visita.setObservacao("Obs");
//        String toString = novaDataVisita;
//
//        if (toString != null) {
//
////
//            System.out.println("Nova data visita: " + novaDataVisita);
//
//            Date dataVisita = new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-20");
//
//            Date dataAtual = new Date();
//            Date horarioAtual = new Date();
//
//            boolean data;
//            if (dataAtual.before(dataVisita) || dataAtual.equals(dataVisita)) {
//                data = true;
//                System.out.println("Correto! A data da visita está após da data atual!");
//                visita.setDataVisita(dataVisita);
//
//                Date horarioVisita = new SimpleDateFormat("HH:mm").parse("00:00");
//                boolean horaVisita;
//
////            if (horarioVisita.after(dataAtual)) {
//                if ((horarioVisita.after(visita.getPedido().getHorarioInicialDisponivel())) || (horarioVisita.equals(visita.getPedido().getHorarioInicialDisponivel())) && (horarioVisita.before(visita.getPedido().getHorarioFinalDisponivel()))) {
//                    horaVisita = true;
//
//                    visita.setHoraVisita(horarioVisita);
//                    visita.setStatus_Visita(Status_Visita.EM_ESPERA);
//
//                    PedidoDAO pedidoDAO = new PedidoDAO();
//                    Pedido pedido = pedidoDAO.getById(idPedido);
//                    pedido.setStatus_Pedido(Status_Pedido.APROVADO);
//                    pedido.setDataDesejada(dataVisita);
//                    visita.setPedido(pedido);
//
//                    pedidoDAO.atualizar(pedido);
//
//                    visitaDAO.atualizar(visita);
//                }
//
//            }
//        } else {
//            visita.setStatus_Visita(Status_Visita.CANCELADA);
//
//            PedidoDAO pedidoDAO = new PedidoDAO();
//            Pedido pedido = pedidoDAO.getById(idPedido);
//            pedido.setStatus_Pedido(Status_Pedido.REPROVADO);
//            visita.setPedido(pedido);
//
//            pedidoDAO.atualizar(pedido);
//
//            visitaDAO.atualizar(visita);
//            System.out.println("A visita foi cancelada e não foi reagendada.");
////                Date dataVisitaAnterior = new SimpleDateFormat("yyyy-MM-dd").parse(pedido.get);
//
////        Instânciando o objeto email
//        }
    }
}
