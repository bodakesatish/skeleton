package com.bodakesatish.skeleton.data.mapper.base

import com.bodakesatish.skeleton.data.source.base.BaseOutput
import com.bodakesatish.skeleton.data.source.base.DataResponseCode
import com.bodakesatish.skeleton.domain.model.response.ResponseCode
import com.bodakesatish.skeleton.domain.usecases.base.BaseUseCase

class BaseOutputRemoteMapper<T : Any> {

    fun mapBaseOutput(output: BaseOutput<T>, response: BaseUseCase.Response<T>) {
        when (output) {
            is BaseOutput.Success -> {
                when (output.code) {
                    DataResponseCode.SUCCESS -> {
                        mapSuccessOutput(output, response)
                    }
                    DataResponseCode.EMPTY -> {
                        mapEmptyOutput(output, response)
                    }
                }
            }

            is BaseOutput.Error -> {
                mapErrorOutput(output, response)
            }
        }
    }

    suspend fun mapBaseOutput(
        output: BaseOutput<T>,
        response: BaseUseCase.Response<T>,
        executeOnSuccess: suspend (T) -> T
    ) {
        when (output) {
            is BaseOutput.Success -> {
                when (output.code) {
                    DataResponseCode.SUCCESS -> {
                        val data = executeOnSuccess.invoke(output.output!!)
                        mapSuccessOutput(data, response)
                    }
                    DataResponseCode.EMPTY -> {
                        mapEmptyOutput(output, response)
                    }
                }
            }
            is BaseOutput.Error -> {
                mapErrorOutput(output, response)
            }
        }
    }

    suspend fun mapBaseOutput(
        output: BaseOutput<T>,
        response: BaseUseCase.Response<T>,
        executeOnSuccess: suspend (T) -> T,
        executeOnError: suspend () -> T
    ) {
        when (output) {
            is BaseOutput.Success -> {
                when (output.code) {
                    DataResponseCode.SUCCESS -> {
                        val data = executeOnSuccess.invoke(output.output!!)
                        mapSuccessOutput(data, response)
                    }
                    DataResponseCode.EMPTY -> {
                        mapEmptyOutput(output, response)
                    }
                }
            }
            is BaseOutput.Error -> {
                mapErrorOutput(output, response, executeOnError)
            }
        }
    }

    suspend fun mapBaseOutput(
        output: BaseOutput<T>,
        response: BaseUseCase.Response<T>,
        executeOnSuccess: suspend (T) -> T,
        executeOnEmpty: suspend () -> T,
        executeOnError: suspend () -> T
    ) {
        when (output) {
            is BaseOutput.Success -> {
                when (output.code) {
                    DataResponseCode.SUCCESS -> {
                        val data = executeOnSuccess.invoke(output.output!!)
                        mapSuccessOutput(data, response)
                    }
                    DataResponseCode.EMPTY -> {
                        val data = executeOnEmpty.invoke()
                        if(data is List<*> && data.size > 0) {
                            mapSuccessOutput(data, response)
                        } else {
                            mapEmptyOutput(data, response)
                        }

                    }
                }
            }
            is BaseOutput.Error -> {
                mapErrorOutput(output, response, executeOnError)
            }
        }
    }


    private fun mapSuccessOutput(output: BaseOutput.Success<T>, response: BaseUseCase.Response<T>) {
            response.setData(output.output)
            response.setResponseCode(ResponseCode.Success)
    }

    private fun mapSuccessOutput(data: T, response: BaseUseCase.Response<T>) {
        response.setData(data)
        response.setResponseCode(ResponseCode.Success)
    }

    private fun mapEmptyOutput(output: BaseOutput.Success<T>, response: BaseUseCase.Response<T>) {
            response.setData(output.output)
            response.setResponseCode(ResponseCode.Empty)
    }

    private fun mapEmptyOutput(data : T , response: BaseUseCase.Response<T>) {
        response.setData(data)
        response.setResponseCode(ResponseCode.Empty)
    }


    private fun mapErrorOutput(output: BaseOutput.Error, response: BaseUseCase.Response<T>) {
        when (output.code) {
            DataResponseCode.NETWORK_ERROR -> {
                response.setResponseCode(ResponseCode.Network)
            }
            DataResponseCode.AUTHENTICATION_FAILED -> {
                response.setResponseCode(ResponseCode.Authentication)
            }
            else -> {
                response.setResponseCode(ResponseCode.Fail)
            }
        }
    }

    private suspend fun mapErrorOutput(
        output: BaseOutput.Error,
        response: BaseUseCase.Response<T>,
        executeOnError: suspend () -> T
    ) {
        val data = executeOnError.invoke()
        response.setData(data)
        when (output.code) {
            DataResponseCode.NETWORK_ERROR -> {
                response.setResponseCode(ResponseCode.Network)
            }
            DataResponseCode.AUTHENTICATION_FAILED -> {
                response.setResponseCode(ResponseCode.Authentication)
            }
            DataResponseCode.UNKNOWN_ERROR -> {
                response.setResponseCode(ResponseCode.Fail)
            }
            else -> {
                response.setResponseCode(ResponseCode.Fail)
            }
        }
    }

}