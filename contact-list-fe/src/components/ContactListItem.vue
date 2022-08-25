<script>
import ProfilePic from "./ProfilePic.vue";
import IconMute from "./icons/IconMute.vue";
import IconMore from "./icons/IconMore.vue";
import DropDown from "./DropDown.vue";
import Button from "./Button.vue";
import { useToast } from "vue-toastification";
import Modal from "./Modal.vue";

export default {
  props: ["contact"],
  components: { ProfilePic, IconMute, IconMore, DropDown, Button, Modal },
  emits: ["refreshContact"],

  setup() {
    const toast = useToast();
    return { toast };
  },
  data() {
    return {
      isContactShow: false,
      isShowMore: false,
      uploadURL: import.meta.env.VITE_API_URL,
      isModalVisible: false,
    };
  },
  methods: {
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    },
  },
};
</script>
<template>
  <div
    class="contact-list-item"
    @mouseover="isContactShow = true"
    @mouseleave="
      () => {
        isContactShow = false;
        isShowMore = false;
      }
    "
  >
    <Modal
      v-if="isModalVisible"
      @close="closeModal"
      @refresh-contact="this.$emit('refreshContact')"
      :contact="contact"
    />
    <div class="contact-info">
      <ProfilePic
        class="profile-picture"
        :url="
          contact.profilePicture
            ? uploadURL + '/uploads/' + contact.profilePicture
            : '/imgs/default.png'
        "
        width="40"
        height="40"
      />
      <div class="contact-details">
        <h3>{{ contact.name }}</h3>
        <span class="message">{{ contact.phoneNumber }}</span>
      </div>
    </div>
    <div v-show="isContactShow" class="contact-options">
      <div class="icon">
        <Button buttonStyle="secondary" icon="IconMute" />
      </div>
      <div class="icon">
        <Button buttonStyle="secondary" icon="IconCall" />
      </div>

      <div class="icon">
        <Button
          buttonStyle="secondary"
          icon="IconMore"
          @mouseover="isShowMore = true"
          @click="isShowMore = false"
        />
      </div>

      <DropDown
        v-if="isShowMore"
        @mouseleave="isShowMore = false"
        :contact="this.contact"
        @refresh-contact="this.$emit('refreshContact')"
        @show-modal="showModal"
      />
    </div>
  </div>
</template>
<style scoped>
.icon {
  position: relative;
}
.contact-list-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  height: 5rem;
}

.contact-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.contact-options {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.profile-picture {
  border: 1px solid var(--primary-70);
}
@media screen and (max-width: 420px) {
  .profile-picture {
    display: none;
  }
}

.contact-details {
  margin-left: 1rem;
  display: flex;
  flex-direction: column;
  align-items: left;
}
.message {
  color: var(--text-secondary);
}
.icon {
  margin-left: 1rem;
  cursor: pointer;
}
</style>
