/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    consultarVisitaProfissionalAJAX();

});

function consultarVisitaProfissionalAJAX() {
    var idProfissional = $(".idProfissional").val();
    $.ajax({
        url: "http://localhost:8080/PFC/consultarVisitasAJAX",
        type: "GET",
        data: {
            id: idProfissional
        },
        success: function (data) {
            if (data === null) {
                dataDisponivel();
            } else {
                verificaDisponibilidade(data);
            }
        }
    });
}

$("#btnAgendar").hide();

function verificaDisponibilidade(data) {
    var objJSON = JSON.parse(data);

    console.log(objJSON);

    $("#btnVerificarDisponibilidade").click(function () {
        $.each(objJSON, function (i) {

            var dataVisitaJSON = objJSON[i].dataVisita;
            var horaVisitaJSON = objJSON[i].horaVisita;
            var dataVisitaJSONFormatada = formatarData(dataVisitaJSON);

            var dataVisita = $("#dataVisita").val();
            var dataVisitaFormatada = formatarData(dataVisita);

            var horarioVisita = $("#horarioVisita").val();
//            dataVisitaBanco diferente da dataVisitaForm ee horaVisitaBanco diferente horaVisitaForm
            if (dataVisitaJSONFormatada !== dataVisitaFormatada && horaVisitaJSON !== horarioVisita) {

                var botao = $("#btnAgendar");
                botao.fadeIn();

            } else {
                var botao = $("#btnAgendar");
                botao.fadeOut();

            }

            console.log(dataVisitaJSONFormatada);
            console.log(dataVisitaFormatada);
            console.log(horaVisitaJSON);
        });
    });
}
/*
$(function () {
    consultarOrcamentoProfissionalAJAX();

});

function consultarOrcamentoProfissionalAJAX() {
    var idProfissional = $(".idProfissional").val();
    $.ajax({
        url: "http://localhost:8080/PFC/consultarOrcamentoAJAX",
        type: "GET",
        data: {
            id: idProfissional
        },
        success: function (data) {
            if (data === null) {
                dataDisponivel();
            } else {
                verificaDisponibilidadeOrcamento(data);
            }
        }
    });
}

$("#btnAgendar").hide();

function verificaDisponibilidadeOrcamento(data) {
    var objJSON = JSON.parse(data);

    console.log(objJSON);

    $("#btnVerificarDisponibilidade").click(function () {
        $.each(objJSON, function (i) {

            var dataPrevistaInicioJSON = objJSON[i].dataPrevistaInicio;
            var horaVisitaJSON = objJSON[i].horaVisita;
            var dataPrevistaInicioJSONFormatada = formatarData(dataPrevistaInicioJSON);

            var dataPrevistaInicio = $("#dataVisita").val();
            var dataPrevistaInicioFormatada = formatarData(dataPrevistaInicio);

            var horaVisita = $("#horarioVisita").val();
//            dataVisitaBanco diferente da dataVisitaForm ee horaVisitaBanco diferente horaVisitaForm
            if (dataPrevistaInicioJSONFormatada !== dataPrevistaInicioFormatada && horaVisitaJSON !== horaVisita) {

                var botao = $("#btnAgendar");
                botao.fadeIn();

            } else {
                var botao = $("#btnAgendar");
                botao.fadeOut();

            }

            console.log(dataPrevistaInicioJSONFormatada);
            console.log(dataPrevistaInicioFormatada);
            console.log(horaVisitaJSON);
        });
    });
}

$(function () {
    consultarAgendaProfissionalAJAX();

});

function consultarAgendaProfissionalAJAX() {
    var idProfissional = $(".idProfissional").val();
    $.ajax({
        url: "http://localhost:8080/PFC/consultarAgendaAJAX",
        type: "GET",
        data: {
            id: idProfissional
        },
        success: function (data) {
            if (data === null) {
                dataDisponivel();
            } else {
                verificaDisponibilidadeAgenda(data);
            }
        }
    });
}

$("#btnAgendar").hide();

function verificaDisponibilidadeAgenda(data) {
    var objJSON = JSON.parse(data);

    console.log(objJSON);

    $("#btnVerificarDisponibilidade").click(function () {
        $.each(objJSON, function (i) {

            var dataAgendaJSON = objJSON[i].dataAgendamento;
            var horaAgendaJSON = objJSON[i].horaAgendamento;
            var dataAgendaJSONFormatada = formatarData(dataAgendaJSON);

            var dataAgenda = $("#dataVisita").val();
            var dataAgendaFormatada = formatarData(dataAgenda);

            var horarioAgenda = $("#horarioVisita").val();
//            dataVisitaBanco diferente da dataVisitaForm ee horaVisitaBanco diferente horaVisitaForm
            if (dataAgendaJSONFormatada !== dataAgendaFormatada && horaAgendaJSON !== horarioAgenda) {

                var botao = $("#btnAgendar");
                botao.fadeIn();

            } else {
                var botao = $("#btnAgendar");
                botao.fadeOut();

            }

            console.log(dataAgendaJSONFormatada);
            console.log(dataAgendaFormatada);
            console.log(horaAgendaJSON);
        });
    });
}
*/
function dataDisponivel() {

    var botao = $("#btnAgendar");
    botao.fadeIn();
}

function formatarData(dataVisita) {

    var d = new Date(dataVisita),
            mes = '' + (d.getMonth() + 1),
            dia = '' + d.getDate(),
            ano = d.getFullYear();

    if (mes.length < 2)
        mes = '0' + mes;
    if (dia.length < 2)
        dia = '0' + dia;
    dia = dia + 1;

    return [dia, mes, ano].join('/'); // "join" é o caracter para separar a formatação da data, neste caso, a barra (/)
}
