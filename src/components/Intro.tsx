import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import P1 from "../assets/intro/intro1.jpg";
import P2 from "../assets/intro/intro2.jpg";
import P3 from "../assets/intro/intro3.jpg";
import "./Intro.css";
import { IonText } from "@ionic/react";

interface ContainerProps {
  onFinish: () => void;
}

const Intro: React.FC<ContainerProps> = () => {
  return (
    <Swiper>
      <SwiperSlide>
        <img src={P1} alt="Picture 1" />
        <IonText>
          <h2>Happy Monthsary Darling</h2>
          <h3>
            Sorry na kung lagi akong pagalit magsalita, wag kang mag-alala hindi
            lang sayo - actually sa lahat, Thank you for staying though.{" "}
          </h3>
        </IonText>
      </SwiperSlide>

      <SwiperSlide>
        <img src={P2} alt="Picture 2" />
        <IonText>
          <h2>You're the best</h2>
          <h3>
            I am so very grateful to have you. Be more patient and gentle if
            there is something you want me to learn.
          </h3>
        </IonText>
      </SwiperSlide>

      <SwiperSlide>
        <img src={P3} alt="Picture 3" />
        <IonText>
          <h2>Can't wait to see you soon...</h2>
          <h3>Godbless😇and take care always🤍🤍🤍</h3>
        </IonText>
      </SwiperSlide>
    </Swiper>
  );
};

export default Intro;
