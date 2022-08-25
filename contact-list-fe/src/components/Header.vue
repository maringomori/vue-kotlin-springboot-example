<script>
import IconBackArrow from "./icons/IconBackArrow.vue";
import IconSettings from "./icons/IconSettings.vue";
import ProfilePic from "./ProfilePic.vue";
import IconLightMode from "./icons/IconLightMode.vue";
import Button from "./Button.vue";
import Modal from "./Modal.vue";

export default {
  name: "Header",
  data() {
    return { isModalVisible: false };
  },
  components: {
    ProfilePic,
    IconLightMode,
    Button,
    Modal,
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
  <header class="header">
    <div class="right"><IconBackArrow class="back-arrow" /></div>
    <div class="icons-mobile">
      <IconBackArrow class="back-arrow" />
      <IconLightMode class="light-mode" />
    </div>
    <div class="middle">
      <h1>Contacts</h1>
      <div class="nav">
        <IconSettings class="settings" />
        <ProfilePic
          class="profile-picture"
          url="/imgs/Timothy.png"
          height="25"
          width="25"
        />
        <Button
          class="add-contact"
          buttonStyle="special"
          icon="IconAdd"
          text="Add item"
          @click="showModal"
        />
        <Modal
          v-show="isModalVisible"
          @close="closeModal"
          @refresh-contact="this.$emit('refreshContact')"
        />
      </div>
    </div>
    <div class="left"><IconLightMode class="light-mode" /></div>
  </header>
</template>

<style scoped>
.header {
  position: absolute;
  width: 100%;
  height: 6rem;
  top: 6rem;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-items: center;
  border-top: 1px solid var(--primary-60);
  border-bottom: 1px solid var(--primary-60);
  z-index: 10;
}

.left,
.right {
  flex: 1 0 0;
  display: flex;
  align-items: center;
}
@media screen and (max-width: 826px) {
  .left,
  .right {
    display: none;
  }
}
.left {
  justify-content: left;
}
.middle {
  flex: 0 0 53%;
  display: flex;
  flex: row;
  align-items: center;
  justify-content: space-between;
  padding: 0 1.5rem;
}
@media screen and (max-width: 826px) {
  .middle {
    flex: 0 0 97%;
    padding-left: 3rem;
  }
}

.icons-mobile {
  display: none;
}
@media screen and (max-width: 826px) {
  .icons-mobile {
    top: -2.5rem;
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    padding: 0 5rem;
  }
}
.right {
  justify-content: right;
}
.back-arrow {
  margin-right: 2.25rem;
  cursor: pointer;
}

.nav {
  display: flex;
  justify-content: center;
  align-items: center;
}
.settings {
  margin-right: 1.75rem;
  cursor: pointer;
}
.profile-picture {
  border: 1.5px solid var(--text-primary);
  margin-right: 2.125rem;
  cursor: pointer;
}
.light-mode {
  margin-left: 2.125rem;
  cursor: pointer;
}

@media screen and (max-width: 826px) {
  .add-contact {
    position: fixed;
    bottom: 2rem;
    right: 2rem;
  }
}
</style>
