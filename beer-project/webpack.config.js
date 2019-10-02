var path = require('path'); // build와 관련된 설정들
 
module.exports = {
    context: path.resolve(__dirname, 'src/main/jsx'), // 이 폴더에 있는 jsx를 쓰겠다.
    entry: {
        init: './index.jsx' // 맨 처음에 시작하는 jsx페이지는 index다.
    },
    devtool: 'sourcemaps',
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/js/react/bundle.js'
    },
    mode: 'none',
    module: {
        rules: [ {
            test: /\.(js|jsx)?$/, // js나 jsx로 끝나는 파일 들에는
            exclude: /(node_modules)/,
            use: {
                loader: 'babel-loader',
                options: {
                    presets: [ '@babel/preset-env', '@babel/preset-react' ] // babel은 이런 것을 쓰도록 설정 해놓음.
                }
            }
        }, {
            test: /\.(css|scss|sass)$/,
            use: [ 'style-loader', 'css-loader','sass-loader' ],
            exclude : /(node_modules)/
        } ]
    }
};