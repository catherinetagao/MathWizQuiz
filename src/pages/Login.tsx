import {
  IonButton,
  IonCard,
  IonCardContent,
  IonContent,
  IonHeader,
  IonIcon,
  IonInput,
  IonPage,
  IonText,
  IonTitle,
  IonToolbar,
  useIonRouter,
} from "@ionic/react";
import React, { useState } from "react";
import {
  keypadOutline,
  logInOutline,
  mailUnreadOutline,
} from "ionicons/icons";
import AK from "../assets/app-cover.jpg";
import Intro from "../components/Intro";
const Login: React.FC = () => {
  const router = useIonRouter();
  const [introSeen, setIntroSeen] = useState(false);

  const doLogin = (event: any) => {
    event.preventDefault();
    console.log("doLogin");
  };

  const finishIntro = async () => {
    console.log("FIN");
  };

  return (
    <>
      {!introSeen ? (
        <Intro onFinish={finishIntro} />
      ) : (
        <IonPage>
          {/* Header */}
          <IonHeader className="ion-text-center">
            <IonToolbar color={"medium"}>
              <IonTitle className="ion-text-uppercase">
                Asuna and Kirito
              </IonTitle>
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
                <form onSubmit={doLogin}>
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
                    Login
                    <IonIcon icon={logInOutline} slot="start" />
                  </IonButton>
                  {/* <IonButton
                type="submit"
                expand="block"
                color={"dark"}
                className="ion-margin-top"
                routerLink="/register"
              >
                Create a new account
                <IonIcon icon={personAddOutline} slot="start" />
              </IonButton> */}
                </form>
              </IonCardContent>
            </IonCard>
          </IonContent>
          {/* Footer */}
          {/* <Footer /> */}
        </IonPage>
      )}
    </>
  );
};

export default Login;
