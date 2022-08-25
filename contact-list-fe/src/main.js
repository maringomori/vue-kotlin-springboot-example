import { createApp } from "vue";
import App from "./App.vue";
import IconAdd from "./components/icons/IconAdd.vue";
import IconBackArrow from "./components/icons/IconBackArrow.vue";
import IconCall from "./components/icons/IconCall.vue";
import IconChange from "./components/icons/IconChange.vue";
import IconDelete from "./components/icons/IconDelete.vue";
import IconFavourite from "./components/icons/IconFavourite.vue";
import IconLightMode from "./components/icons/IconLightMode.vue";
import IconMore from "./components/icons/IconMore.vue";
import IconMute from "./components/icons/IconMute.vue";
import IconSearch from "./components/icons/IconSearch.vue";
import IconSettings from "./components/icons/IconSettings.vue";
import Toast from "vue-toastification";
// Import the CSS or use your own!
import "vue-toastification/dist/index.css";

import "./assets/main.css";

const app = createApp(App);

//Add icons
app.component("IconAdd", IconAdd);
app.component("IconBackArrow", IconBackArrow);
app.component("IconCall", IconCall);
app.component("IconChange", IconChange);
app.component("IconDelete", IconDelete);
app.component("IconFavourite", IconFavourite);
app.component("IconLightMode", IconLightMode);
app.component("IconMore", IconMore);
app.component("IconMute", IconMute);
app.component("IconSearch", IconSearch);
app.component("IconSettings", IconSettings);

const options = {
  // You can set your default options here
};

app.use(Toast, options);

const mountedApp = app.mount("#app");
