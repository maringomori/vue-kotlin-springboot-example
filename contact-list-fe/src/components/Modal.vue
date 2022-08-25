<script>
import ProfilePic from "./ProfilePic.vue";
import Button from "./Button.vue";
import InputField from "./InputField.vue";
import { updateContact } from "../api/contacts";
import { createContact } from "../api/contacts";
import { useToast } from "vue-toastification";

export default {
  name: "Modal",
  props: ["contact"],
  setup() {
    const toast = useToast();
    return { toast };
  },
  data() {
    return {
      defaultImg: "/imgs/default.png",
      imgSrc: "/imgs/default.png",
      file: "",
      edit: false,
      name: "",
      phoneNumber: "",
      email: "",
      id: "",
    };
  },
  mounted() {
    if (this.contact) {
      if (this.contact.profilePicture !== null) {
        this.imgSrc =
          import.meta.env.VITE_API_URL +
          "/uploads/" +
          this.contact.profilePicture;
      } else {
        this.imgSrc = this.defaultImg;
      }
      this.id = this.contact.id;
      this.name = this.contact.name;
      this.phoneNumber = this.contact.phoneNumber;
      this.email = this.contact.email;
      this.edit = true;
    }
  },
  methods: {
    close() {
      this.$emit("close");
    },
    onFile(e) {
      const files = e.target.files;
      this.file = files[0];
      if (!files.length) return;
      const reader = new FileReader();
      reader.readAsDataURL(files[0]);
      reader.onload = () => (this.imgSrc = reader.result);
    },
    async createNewContact() {
      await createContact(
        this.name,
        this.phoneNumber,
        this.email,
        this.imgSrc === this.defaultImg ? undefined : this.file
      )
        .then((response) => {
          this.$emit("refreshContact");
          this.close();
        })
        .catch((error) => {
          this.toast.error(error.response.data.message);
        });
    },
    async updateExistingContact() {
      await updateContact(
        this.id,
        this.name,
        this.phoneNumber,
        this.email,
        this.imgSrc === this.defaultImg ? undefined : this.file
      )
        .then((response) => {
          this.$emit("refreshContact");
          this.toast.error("contact");
          this.close();
        })
        .catch((error) => {
          this.toast.error(error.response.data.message);
        });
    },
  },
  components: { ProfilePic, Button, Button, Button, InputField },
};
</script>
<template>
  <div class="modal-backdrop" @click.self="close">
    <div class="modal">
      <h2>Add contact</h2>
      <div class="profile-picture-container">
        <ProfilePic
          class="profile-picture"
          :url="imgSrc"
          width="88"
          height="88"
        />
        <Button
          v-if="this.defaultImg === this.imgSrc"
          buttonStyle="primary"
          icon="IconAdd"
          text="Add picture"
          @click="$refs.file.click()"
        />
        <Button
          v-if="this.defaultImg !== this.imgSrc"
          buttonStyle="primary"
          icon="IconChange"
          text="Change picture"
          @click="$refs.file.click()"
        />
        <Button
          v-if="this.defaultImg !== this.imgSrc"
          class="delete-button"
          buttonStyle="primary"
          icon="IconDelete"
          @click="this.imgSrc = this.defaultImg"
        />

        <input
          ref="file"
          id="file-upload"
          name="img"
          accept="image/*"
          type="file"
          @change="onFile"
        />
      </div>
      <div class="inputs">
        <InputField
          v-model="name"
          :value="name"
          label="Name"
          placeholder="Jamie Wright"
        />
        <InputField
          v-model="phoneNumber"
          label="Phone number"
          placeholder="+01 234 5678"
          :value="phoneNumber"
        />
        <InputField
          v-model="email"
          :value="email"
          label="Email address"
          placeholder="jamie.wright@gmail.com"
        />
      </div>
      <div class="modal-footer">
        <Button buttonStyle="secondary" text="Cancel" @click="close" />
        <Button
          class="done"
          buttonStyle="primary"
          text="Done"
          @click="edit ? updateExistingContact() : createNewContact()"
        />
      </div>
    </div>
  </div>
</template>
<style scoped>
input[type="file"] {
  display: none;
}
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 99;
}

.modal {
  display: flex;
  flex-direction: column;
  background-color: var(--primary-100);
  padding: 1.5rem;
  border-radius: 8px;
  z-index: 100;
}

.profile-picture-container {
  margin-top: 1.5rem;
  display: flex;
  flex-direction: row;
  justify-items: center;
  align-items: center;
}
.profile-picture {
  margin-right: 1rem;
  border: 1px solid var(--primary-70);
}

.delete-button {
  margin-left: 0.5rem;
}
.modal-footer {
  margin-top: 3rem;
  display: flex;
  flex-direction: row;
  justify-content: right;
  align-items: center;
}
.done {
  margin-left: 1rem;
}
</style>
