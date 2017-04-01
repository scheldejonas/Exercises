module.exports = {
  entry: './src/index.js',
  
  output: {
    filename: 'bundle.js'
  },
  
  module: {
    loaders: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        loaders: [
            'react-hot-loader',
            'babel-loader'
        ]
      },
      {
        test: /\.css$/,
        loaders: [
            'style-loader',
            'css-loader'
        ]
      }
    ]
  }
};