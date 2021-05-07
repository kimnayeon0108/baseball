import React from "react";
import GamePageHeader from "Components/GamePage/GamePageHeader";
import MainContainer from "./MainContainer";
import Popup from "Components/GamePage/Popup";
import styled from "styled-components";

const GamePage = () => {
  return (
    <GamePageBackground>
      <Popup />
      <GamePageHeader />
      <MainContainer />
    </GamePageBackground>
  );
};

const GamePageBackground = styled.div`
  height: 100vh;
  background: black;
  opacity: 0.9;
`;

export default GamePage;