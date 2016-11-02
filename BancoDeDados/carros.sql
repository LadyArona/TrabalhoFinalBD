-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 02-Nov-2016 às 20:44
-- Versão do servidor: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carros`
--
CREATE DATABASE IF NOT EXISTS `carros` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `carros`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `autodromo`
--

CREATE TABLE `autodromo` (
  `aut_codigo` int(11) NOT NULL,
  `aut_descricao` varchar(100) DEFAULT NULL,
  `aut_nome_pista` varchar(100) DEFAULT NULL,
  `aut_uf` char(2) DEFAULT NULL,
  `aut_cidade` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `campeonato`
--

CREATE TABLE `campeonato` (
  `cam_codigo` int(11) NOT NULL,
  `cam_descricao` varchar(100) DEFAULT NULL,
  `cam_data_inicial` date DEFAULT NULL,
  `cam_data_final` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `campeonato_internacional`
--

CREATE TABLE `campeonato_internacional` (
  `cam_codigo` int(11) NOT NULL,
  `cai_codigo_internacional` varchar(15) DEFAULT NULL,
  `cai_iec` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `campeonato_nacional`
--

CREATE TABLE `campeonato_nacional` (
  `cam_codigo` int(11) NOT NULL,
  `can_codigo_nacional` varchar(8) DEFAULT NULL,
  `can_nbr` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `corrida`
--

CREATE TABLE `corrida` (
  `cor_codigo` int(11) NOT NULL,
  `cor_data` date DEFAULT NULL,
  `cor_hora` time DEFAULT NULL,
  `cor_descricao` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `corrida_campeonato`
--

CREATE TABLE `corrida_campeonato` (
  `cor_codigo` int(11) NOT NULL,
  `cam_codigo` int(11) NOT NULL,
  `aut_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `equipe`
--

CREATE TABLE `equipe` (
  `eqp_codigo` int(11) NOT NULL,
  `eqp_nome` varchar(100) DEFAULT NULL,
  `eqp_data_formacao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ingresso`
--

CREATE TABLE `ingresso` (
  `ing_codigo` int(11) NOT NULL,
  `ing_valor` varchar(45) DEFAULT NULL,
  `ven_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `patrocinador`
--

CREATE TABLE `patrocinador` (
  `pat_codigo` int(11) NOT NULL,
  `pat_cnpj` varchar(14) DEFAULT NULL,
  `pat_nome` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `patrocinio`
--

CREATE TABLE `patrocinio` (
  `eqp_codigo` int(11) NOT NULL,
  `pat_codigo` int(11) NOT NULL,
  `peq_valor_investimento` decimal(18,2) DEFAULT NULL,
  `pat_data_inicial` datetime DEFAULT NULL,
  `pat_data_final` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `piloto`
--

CREATE TABLE `piloto` (
  `pil_codigo` int(11) NOT NULL,
  `pil_data_nascimento` int(11) DEFAULT NULL,
  `pil_uf_naturalidade` char(2) DEFAULT NULL,
  `pil_cpf` varchar(11) DEFAULT NULL,
  `pil_nome` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `piloto_corrida`
--

CREATE TABLE `piloto_corrida` (
  `pil_codigo` int(11) NOT NULL,
  `cor_codigo` int(11) NOT NULL,
  `pic_posicao_largada` int(11) DEFAULT NULL,
  `pic_posicao_chegada` int(11) DEFAULT NULL,
  `pic_pontuacao` decimal(18,2) DEFAULT NULL,
  `pic_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `piloto_equipe`
--

CREATE TABLE `piloto_equipe` (
  `pil_codigo` int(11) NOT NULL,
  `eqp_codigo` int(11) NOT NULL,
  `peq_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `vei_codigo` int(11) NOT NULL,
  `vei_marca` varchar(30) DEFAULT NULL,
  `vei_modelo` varchar(20) DEFAULT NULL,
  `vei_ano` int(11) DEFAULT NULL,
  `vei_velocidade_max` decimal(18,2) DEFAULT NULL,
  `vei_placa` varchar(45) DEFAULT NULL,
  `piloto_pil_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `venda_ingresso`
--

CREATE TABLE `venda_ingresso` (
  `ven_codigo` int(11) NOT NULL,
  `ven_valor` decimal(18,2) DEFAULT NULL,
  `ven_quantidade_max` int(11) DEFAULT NULL,
  `cor_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autodromo`
--
ALTER TABLE `autodromo`
  ADD PRIMARY KEY (`aut_codigo`);

--
-- Indexes for table `campeonato`
--
ALTER TABLE `campeonato`
  ADD PRIMARY KEY (`cam_codigo`);

--
-- Indexes for table `campeonato_internacional`
--
ALTER TABLE `campeonato_internacional`
  ADD PRIMARY KEY (`cam_codigo`),
  ADD KEY `fk_campeonato_internacional_campeonato1_idx` (`cam_codigo`);

--
-- Indexes for table `campeonato_nacional`
--
ALTER TABLE `campeonato_nacional`
  ADD PRIMARY KEY (`cam_codigo`),
  ADD KEY `fk_campeonato_nacional_campeonato1_idx` (`cam_codigo`);

--
-- Indexes for table `corrida`
--
ALTER TABLE `corrida`
  ADD PRIMARY KEY (`cor_codigo`);

--
-- Indexes for table `corrida_campeonato`
--
ALTER TABLE `corrida_campeonato`
  ADD PRIMARY KEY (`cor_codigo`,`cam_codigo`),
  ADD KEY `fk_corrida_has_campeonato_campeonato1_idx` (`cam_codigo`),
  ADD KEY `fk_corrida_has_campeonato_corrida1_idx` (`cor_codigo`),
  ADD KEY `fk_corrida_campeonato_autodromo1_idx` (`aut_codigo`);

--
-- Indexes for table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`eqp_codigo`);

--
-- Indexes for table `ingresso`
--
ALTER TABLE `ingresso`
  ADD PRIMARY KEY (`ing_codigo`),
  ADD KEY `fk_ingresso_venda_ingresso1_idx` (`ven_codigo`);

--
-- Indexes for table `patrocinador`
--
ALTER TABLE `patrocinador`
  ADD PRIMARY KEY (`pat_codigo`);

--
-- Indexes for table `patrocinio`
--
ALTER TABLE `patrocinio`
  ADD PRIMARY KEY (`eqp_codigo`,`pat_codigo`),
  ADD KEY `fk_equipe_has_patrocinador_patrocinador1_idx` (`pat_codigo`),
  ADD KEY `fk_equipe_has_patrocinador_equipe1_idx` (`eqp_codigo`);

--
-- Indexes for table `piloto`
--
ALTER TABLE `piloto`
  ADD PRIMARY KEY (`pil_codigo`);

--
-- Indexes for table `piloto_corrida`
--
ALTER TABLE `piloto_corrida`
  ADD PRIMARY KEY (`cor_codigo`,`pic_codigo`,`pil_codigo`),
  ADD KEY `fk_piloto_has_corrida_corrida1_idx` (`cor_codigo`),
  ADD KEY `fk_piloto_has_corrida_piloto1_idx` (`pil_codigo`);

--
-- Indexes for table `piloto_equipe`
--
ALTER TABLE `piloto_equipe`
  ADD PRIMARY KEY (`pil_codigo`,`peq_codigo`),
  ADD KEY `fk_piloto_has_equipe_equipe1_idx` (`eqp_codigo`),
  ADD KEY `fk_piloto_has_equipe_piloto1_idx` (`pil_codigo`);

--
-- Indexes for table `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`vei_codigo`),
  ADD KEY `fk_veiculo_piloto_idx` (`piloto_pil_codigo`);

--
-- Indexes for table `venda_ingresso`
--
ALTER TABLE `venda_ingresso`
  ADD PRIMARY KEY (`ven_codigo`),
  ADD KEY `fk_venda_ingresso_corrida1_idx` (`cor_codigo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autodromo`
--
ALTER TABLE `autodromo`
  MODIFY `aut_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `campeonato`
--
ALTER TABLE `campeonato`
  MODIFY `cam_codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `corrida`
--
ALTER TABLE `corrida`
  MODIFY `cor_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `eqp_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ingresso`
--
ALTER TABLE `ingresso`
  MODIFY `ing_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `patrocinador`
--
ALTER TABLE `patrocinador`
  MODIFY `pat_codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `piloto`
--
ALTER TABLE `piloto`
  MODIFY `pil_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `vei_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `venda_ingresso`
--
ALTER TABLE `venda_ingresso`
  MODIFY `ven_codigo` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `campeonato_internacional`
--
ALTER TABLE `campeonato_internacional`
  ADD CONSTRAINT `fk_campeonato_internacional_campeonato1` FOREIGN KEY (`cam_codigo`) REFERENCES `campeonato` (`cam_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `campeonato_nacional`
--
ALTER TABLE `campeonato_nacional`
  ADD CONSTRAINT `fk_campeonato_nacional_campeonato1` FOREIGN KEY (`cam_codigo`) REFERENCES `campeonato` (`cam_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `corrida_campeonato`
--
ALTER TABLE `corrida_campeonato`
  ADD CONSTRAINT `fk_corrida_campeonato_autodromo1` FOREIGN KEY (`aut_codigo`) REFERENCES `autodromo` (`aut_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_corrida_has_campeonato_campeonato1` FOREIGN KEY (`cam_codigo`) REFERENCES `campeonato` (`cam_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_corrida_has_campeonato_corrida1` FOREIGN KEY (`cor_codigo`) REFERENCES `corrida` (`cor_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `ingresso`
--
ALTER TABLE `ingresso`
  ADD CONSTRAINT `fk_ingresso_venda_ingresso1` FOREIGN KEY (`ven_codigo`) REFERENCES `venda_ingresso` (`ven_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `patrocinio`
--
ALTER TABLE `patrocinio`
  ADD CONSTRAINT `fk_equipe_has_patrocinador_equipe1` FOREIGN KEY (`eqp_codigo`) REFERENCES `equipe` (`eqp_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_equipe_has_patrocinador_patrocinador1` FOREIGN KEY (`pat_codigo`) REFERENCES `patrocinador` (`pat_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `piloto_corrida`
--
ALTER TABLE `piloto_corrida`
  ADD CONSTRAINT `fk_piloto_has_corrida_corrida1` FOREIGN KEY (`cor_codigo`) REFERENCES `corrida` (`cor_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_piloto_has_corrida_piloto1` FOREIGN KEY (`pil_codigo`) REFERENCES `piloto` (`pil_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `piloto_equipe`
--
ALTER TABLE `piloto_equipe`
  ADD CONSTRAINT `fk_piloto_has_equipe_equipe1` FOREIGN KEY (`eqp_codigo`) REFERENCES `equipe` (`eqp_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_piloto_has_equipe_piloto1` FOREIGN KEY (`pil_codigo`) REFERENCES `piloto` (`pil_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `veiculo`
--
ALTER TABLE `veiculo`
  ADD CONSTRAINT `fk_veiculo_piloto` FOREIGN KEY (`piloto_pil_codigo`) REFERENCES `piloto` (`pil_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `venda_ingresso`
--
ALTER TABLE `venda_ingresso`
  ADD CONSTRAINT `fk_venda_ingresso_corrida1` FOREIGN KEY (`cor_codigo`) REFERENCES `corrida` (`cor_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
