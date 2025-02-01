import {
  IonContent,
  IonFooter,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import React from "react";

const Footer: React.FC = () => {
  return (
    <IonFooter className="ion-text-center">
      <IonToolbar>
        <IonTitle>MathWizQuiz</IonTitle>
      </IonToolbar>
    </IonFooter>
  );
};

export default Footer;
