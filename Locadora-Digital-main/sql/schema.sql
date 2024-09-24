CREATE TABLE `jogo`(
  `id` int(10) NOT NULL,
  `titulo` varchar(60) NOT NULL,
  `preco` decimal(10,2),
  `descricao` text,
  `numeroDias` int(10) NOT NULL,
  `memoria` int(10) DEFAULT NULL,
  `plataforma` varchar(60) NOT NULL
) ENGINE=InnoDb DEFAULT CHARSET=latin1;

CREATE TABLE `itemlocacao` (
  `id` int(11) NOT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `Jogo_id` int(11) DEFAULT NULL,
  `Locacao_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `locacao` (
  `id` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `itemlocacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ItemLocacao_Jogo1_idx` (`Jogo_id`),
  ADD KEY `fk_ItemLocacao_Locacao1_idx` (`Locacao_id`);

ALTER TABLE `jogo`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `locacao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Locacao_Usuario` (`usuario_id`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `itemlocacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `jogo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `locacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `locacao`
  ADD CONSTRAINT `fk_Locacao_Usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`); 