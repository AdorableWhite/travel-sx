<template>
  <view class="container">
    <SectionTitle title="话题圈子" extra="分享你的游记" />
    <TagGroup :tags="topics.map((item) => item.name)" />

    <view class="section">
      <SectionTitle title="发布游记" />
      <view class="form">
        <input v-model="form.title" class="input" placeholder="标题" />
        <textarea v-model="form.content" class="textarea" placeholder="写下你的旅途记忆" />
        <button class="btn" @click="submitNote">发布</button>
      </view>
    </view>

    <view class="section">
      <SectionTitle title="精选游记" />
      <InfoCard v-for="item in notes" :key="item.id" :title="item.title">
        <view class="card-desc">{{ item.content }}</view>
      </InfoCard>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchNotes, fetchTopics, createNote } from '../../services/api';
import { mockNotes, mockTopics } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';
import TagGroup from '../../components/TagGroup.vue';

const topics = ref(mockTopics);
const notes = ref(mockNotes);
const form = ref({
  title: '',
  content: '',
});

const load = async () => {
  try {
    const topicRes = await fetchTopics(10);
    if (topicRes && topicRes.data) {
      topics.value = topicRes.data;
    }
    const noteRes = await fetchNotes(null, 10);
    if (noteRes && noteRes.data) {
      notes.value = noteRes.data;
    }
  } catch (e) {
    topics.value = mockTopics;
    notes.value = mockNotes;
  }
};

const submitNote = async () => {
  if (!form.value.title || !form.value.content) {
    uni.showToast({ title: '请填写标题和内容', icon: 'none' });
    return;
  }
  try {
    const res = await createNote({
      userId: 1,
      topicId: topics.value[0]?.id || null,
      title: form.value.title,
      content: form.value.content,
      coverImage: '',
    });
    if (res && res.data) {
      notes.value = [res.data, ...notes.value];
      form.value.title = '';
      form.value.content = '';
    }
  } catch (e) {
    uni.showToast({ title: '发布失败', icon: 'none' });
  }
};

onMounted(load);
</script>

<style scoped lang="scss">
.form {
  background: #fff;
  padding: 12px;
  border-radius: 10px;
}

.input,
.textarea {
  width: 100%;
  background: #f7f5f1;
  border-radius: 8px;
  padding: 8px;
  margin-bottom: 8px;
}

.textarea {
  min-height: 80px;
}

.btn {
  background: #9a2e2e;
  color: #fff;
  border-radius: 8px;
  padding: 8px;
}

.card-desc {
  font-size: 12px;
  color: #7a6f64;
}
</style>
