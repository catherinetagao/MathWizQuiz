import {
  IonBackButton,
  IonButton,
  IonButtons,
  IonCard,
  IonCardContent,
  IonContent,
  IonFooter,
  IonHeader,
  IonIcon,
  IonInput,
  IonPage,
  IonText,
  IonTitle,
  IonToolbar,
  useIonRouter,
} from "@ionic/react";
import React from "react";
import {
  checkboxOutline,
  keypadOutline,
  mailUnreadOutline,
} from "ionicons/icons";
import AK from "../assets/app-cover.jpg";

const Register: React.FC = () => {
  const router = useIonRouter();

  const doRegister = (event: any) => {
    event.preventDefault();
    console.log(doRegister);
    router.goBack();
  };
  return (
    <IonPage>
      {/* Header */}
      <IonHeader>
        <IonToolbar color="medium">
          <IonButtons slot="start">
            <IonBackButton defaultHref="/" />
          </IonButtons>
          <IonTitle className="ion-text-center">Create Account</IonTitle>
        </IonToolbar>
      </IonHeader>

      {/* Content */}
      <IonContent className="ion-padding">
        {/* <IonText>Welcome to this app</IonText> */}
        <img
          src={AK}
          alt="Asuna and Kirito"
          className="ion-margin-top ion-margin-bottom"
          style={{
            borderRadius: "100px",
            width: "50%",
            display: "block",
            margin: "0 auto",
          }}
        />
        <IonCard>
          <IonCardContent>
            <form onSubmit={doRegister}>
              <IonInput
                fill="outline"
                labelPlacement="floating"
                label="Email"
                type="email"
                placeholder="kathsandy@gmail.com"
              >
                <IonIcon slot="start" icon={mailUnreadOutline} />
              </IonInput>
              <IonInput
                className="ion-margin-top"
                fill="outline"
                labelPlacement="floating"
                label="Password"
                type="password"
                placeholder="Our monthsary?"
              >
                <IonIcon slot="start" icon={keypadOutline} />
              </IonInput>
              <IonButton
                type="submit"
                expand="block"
                color={"medium"}
                className="ion-margin-top"
              >
                Create my account
                <IonIcon icon={checkboxOutline} slot="start" />
              </IonButton>
            </form>
          </IonCardContent>
        </IonCard>
      </IonContent>
      {/* Footer */}
      {/* <Footer /> */}
    </IonPage>
  );
};

export default Register;
