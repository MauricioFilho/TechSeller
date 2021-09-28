<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="../css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="../css/bootstrap2-toggle.min.css"/>
    <link rel="stylesheet" href="../css/bootstrap.css"/>
    <link rel="stylesheet" href="../css/font-awesome.css"/>
    <link rel="stylesheet" href="../css/tv-rodape.css"/>
    <link rel="stylesheet" href="../css/tv.css"/>
    <link rel="stylesheet" href="../css/bootstrap-responsive.css"/>
    <title>Cadastro Clientes</title>
</head>
<body>
    <form class="form-horizontal" name="cadastroClienteForm" action="cadastro-cliente" method="post">
        <div class="page-header">
            <h1>Cadastro Cliente</h1>
        </div>
        <div class="col-xs-3">
            <div class="checkbox span3">
                <label>C&oacute;digo</label>
                <input class="input-group text-center"
                       type="text"
                       name="idCliente"
                       size="4"
                       autofocus
                       required>
            </div>
            <div class="checkbox span3">
                <label>Nome</label>
                <input class="input-group"
                       type="text"
                       name="nomeCliente"
                       size="35"
                       required>
            </div>
            <div class="checkbox span2">
                <label>CPF</label>
                <input class="input-group"
                       type="text"
                       name="cpfCliente"
                       size="9"
                       required >
            </div>
            <div class="checkbox span2">
                <label>Telefone</label>
                <input class="input-group"
                       type="tel"
                       name="telefoneCliente"
                       size="13">
            </div>
            <div class="checkbox span3">
                <label>Email</label>
                <input class="input-group"
                       type="text"
                       name="emailCliente"
                       size="35" >
            </div>
            <div class="checkbox span3">
                <label>CEP</label>
                <input class="input-group"
                       type="text"
                       name="cepCliente"
                       size="10">
            </div>
            <div class="checkbox span">
                <label>Cidade</label>
                <input class="input-group"
                       type="text"
                       name="cidadeCliente"
                       size="13">
            </div>
            <div class="checkbox span1">
                <label>UF</label>
                <input class="input-group"
                       type="text"
                       name="cepCliente"
                       size="2">
            </div>
            <div class="checkbox span4">
                <label>Rua</label>
                <input class="input-group"
                       type="text"
                       name="ruaCliente"
                       size="35" >
            </div>
            <div class="checkbox span2">
                <label>Bairro</label>
                <input class="input-group"
                       type="text"
                       name="bairroCliente"
                       size="12" >
            </div>
            <div class="checkbox span1">
                <label>Pa&iacute;s</label>
                <input class="input-group"
                       type="text"
                       name="paisCliente"
                       size="13%">
            </div>
            <div class="checkbox span4">
                <input class="btn-group btn-default" type="submit" value="Salvar">
            </div>
        </div>
    </form>
</body>
</html>