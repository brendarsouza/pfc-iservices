/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
www.moinho.net
Verify empty state of an string. Including spaces
Verifica se uma vari�vel est� vazia. Incluindo espa�os em branco
Fucntion: isEmpty
Return  : true if the string is empty
Retorno : true se a string estiver vaiz
e-mail  : celso.goya@moinho.net
Author  : Celso Goya

Instructions
If you have any questions about the functionality or sugestions please send us a report.

Instru��es
Se voc� tiver qualquer d�vida ou sugest�o sobre a funcionalidade desta fun��o por favor envie-nos um e-mail
*/
function isEmpty(pStrText){
	var	len = pStrText.length;
	var pos;
	var vStrnewtext = "";

	for (pos=0; pos<len; pos++){
		if (pStrText.substring(pos, (pos+1)) != " "){
			vStrnewtext = vStrnewtext + pStrText.substring(pos, (pos+1));
		}
	}

	if (vStrnewtext.length > 0)
		return false;
	else
		return true;
}
