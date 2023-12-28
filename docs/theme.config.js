/* eslint-disable import/no-anonymous-default-export */

export default {
  projectLink: 'https://github.com/beaverfy/react-native-wear', // GitHub link in the navbar
  docsRepositoryBase:
    'https://github.com/beaverfy/react-native-wear', // base URL for the docs repository
  nextLinks: true,
  prevLinks: true,
  search: true,
  customSearch: null, // customizable, you can use algolia for example
  darkMode: true,
  footer: true,
  footerText: `By Beaverfy & Community`,
  footerEditLink: `Edit this page on GitHub`,
  logo: (
    <>
      <img
        src="/react-native-wear.png"
        width="20"
        alt="React Native Wear"
        style={{ marginRight: '10px' }}
      />
      <span>React Native Wear</span>
    </>
  ),
  head: (
    <>
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta
        name="description"
        content="Module for interacting with Wear OS via React Native"
      />
      <meta name="og:title" content="ActionSheet for React Native" />
    </>
  ),
};
